package com.addicts.rph.matching.security;

import com.addicts.rph.matching.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * @author sravantatikonda
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";

  private static final String TOKEN_PREFIX = "Bearer ";

  private static String SIGNING_KEY;

  public JwtAuthorizationFilter(AuthenticationManager authManager, String signingKey) {
    super(authManager);
    this.SIGNING_KEY = signingKey;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader(AUTHORIZATION_HEADER);
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    try {
      UsernamePasswordAuthenticationToken authentication = resolveToken(request);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(request, response);
    } catch (ExpiredJwtException exception) {
      handleError(response, "expired token");
    } /*catch (Exception exception) {
      handleError(response, "invalid token");
    }*/
  }

  private UsernamePasswordAuthenticationToken resolveToken(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION_HEADER);
    if (token != null) {
      token = token.replace(TOKEN_PREFIX, "");

      Claims body = Jwts.parser()
          .setSigningKey(SIGNING_KEY.getBytes())
          .parseClaimsJws(token)
          .getBody();
      String email = body.get("email", String.class);
      if (email != null) {

        JwtBody jwtBody = new JwtBody();
        jwtBody.setUserId((Integer) body.get("userId"));
        jwtBody.setFirstname((String) body.get("firstName"));
        jwtBody.setLastname((String) body.get("lastName"));
        jwtBody.setEmail((String) body.get("email"));
        jwtBody.setClientId((String) body.get("client_id"));
        ArrayList<GrantedAuthority> authorities = (ArrayList<GrantedAuthority>) Optional
            .ofNullable(body.get("authorities", List.class))
            .map(auths -> auths.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.toString()))
                .collect(Collectors.toCollection(ArrayList<GrantedAuthority>::new)))
            .orElse(new ArrayList<GrantedAuthority>());

        return new UsernamePasswordAuthenticationToken(email, jwtBody, authorities);
      }
      return null;
    }
    return null;
  }

  private void handleError(HttpServletResponse response, String errorMessage) throws IOException {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrorCode(HttpServletResponse.SC_UNAUTHORIZED);
    errorResponse.setErrorMessage(errorMessage);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
    PrintWriter writer = response.getWriter();
    ObjectMapper mapper = new ObjectMapper();
    writer.write(mapper.writeValueAsString(errorResponse));
    writer.flush();
  }
}
