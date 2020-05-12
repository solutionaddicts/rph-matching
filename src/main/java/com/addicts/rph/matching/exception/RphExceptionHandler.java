package com.addicts.rph.matching.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author sravantatikonda
 */
@Slf4j
@RestControllerAdvice
public class RphExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
      WebRequest request) {
    log.info("RphApiExceptionHandler- MethodArgumentNotValidException", ex);
    BindingResult result = ex.getBindingResult();
    ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();

    List<FieldError> fieldErrors = result.getFieldErrors();

    for (FieldError fieldError : fieldErrors) {
      Violation errorResponse = new Violation(fieldError.getField(),
          fieldError.getDefaultMessage());
      validationErrorResponse.getViolations().add(errorResponse);
    }
    return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<RphExceptionResponse> handleRunTimeException(RuntimeException ex)
      throws RphException {

    log.error("RphApiExceptionHandler- RuntimeException ", ex);

    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.INTERNAL_SERVER_ERROR.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<RphExceptionResponse> handleIllegalArgumentException(
      IllegalArgumentException ex) {

    log.error("RphApiExceptionHandler- IllegalArgumentException ", ex);
    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.BAD_REQUEST.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RphAuthenticationException.class)
  public ResponseEntity<RphExceptionResponse> handleRphAuthenticationException(
      RphAuthenticationException ex) {

    log.error("RphApiExceptionHandler- RphAuthenticationException ", ex);
    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')),
        HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
  }

  @ExceptionHandler(RphAuthorizationException.class)
  public ResponseEntity<RphExceptionResponse> handleRphAuthException(RphAuthorizationException ex) {

    log.error("RphApiExceptionHandler- RphAuthException ", ex);
    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.UNAUTHORIZED.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NoResultException.class)
  public ResponseEntity<RphExceptionResponse> handleNoResultException(
      NoResultException ex) {

    log.error("RphApiExceptionHandler- NoResultException ", ex);
    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.BAD_REQUEST.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RphInvalidTokenException.class)
  public ResponseEntity<RphExceptionResponse> handleInvalidTokenException(
      RphInvalidTokenException ex) {

    log.error("RphApiExceptionHandler- RphInvalidTokenException ", ex);

    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.FORBIDDEN.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(RphValidationException.class)
  public ResponseEntity<RphExceptionResponse> handleValidationException(RphValidationException ex) {

    log.error("RphApiExceptionHandler- RphValidationException ", ex);

    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.PRECONDITION_REQUIRED.value(),
        message.substring(message.indexOf('-') + 2));
    return new ResponseEntity<>(exception, HttpStatus.PRECONDITION_REQUIRED);
  }

  @ExceptionHandler(RphNotFoundException.class)
  public ResponseEntity<RphExceptionResponse> handleNotFoundException(RphNotFoundException ex) {

    log.error("RphApiExceptionHandler- RphNotFoundException ", ex);
    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.NOT_FOUND.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RphBadRequestException.class)
  public ResponseEntity<RphExceptionResponse> handleBadRequestException(RphBadRequestException ex) {

    log.error("RphApiExceptionHandler- RphBadRequestException ", ex);
    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.BAD_REQUEST.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<RphExceptionResponse> handleEntityNotFoundException(
      EntityNotFoundException ex) {

    log.error("RphApiExceptionHandler- EntityNotFoundException ", ex);
    String message = ex.getMessage();
    RphExceptionResponse exception = new RphExceptionResponse(
        message.substring(0, message.indexOf('-')), HttpStatus.NOT_FOUND.value(),
        message.substring(message.indexOf('-') + 2));

    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }

/*  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<HttpMessageNotReadableException> handleEntityNotFoundException(
      HttpMessageNotReadableException ex) {

    log.error("RphApiExceptionHandler- HttpMessageNotReadableException ", ex);
    RphExceptionResponse exception =
        new RphExceptionResponse(HttpStatus.BAD_REQUEST.value(),
            ExceptionUtils.getRootCauseMessage(ex));
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }*/

  /*// 400
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    log.info(ex.getClass().getName());

    final List<String> errors = new ArrayList<>();
    for (final FieldError error - ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + "- " + error.getDefaultMessage());
    }
    for (final ObjectError error - ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + "- " + error.getDefaultMessage());
    }
    final RphApiError rphApiError = new RphApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, rphApiError, headers, rphApiError.getStatus(), request);
  }*/

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    log.info(ex.getClass().getName());

    final List<String> errors = new ArrayList<>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    final RphApiError rphApiError = new RphApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, rphApiError, headers, rphApiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    log.info(ex.getClass().getName());
    final String error =
        ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex
            .getRequiredType();

    final RphApiError rphApiError = new RphApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      final MissingServletRequestPartException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    log.info(ex.getClass().getName());
    final String error = ex.getRequestPartName() + " part is missing";
    final RphApiError rphApiError = new RphApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    log.info(ex.getClass().getName());
    final String error = ex.getParameterName() + " parameter is missing";
    final RphApiError rphApiError = new RphApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  //

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      final MethodArgumentTypeMismatchException ex, final WebRequest request) {
    log.info(ex.getClass().getName());
    final String error = ex.getName() + " should be of type " + Objects
        .requireNonNull(ex.getRequiredType()).getName();

    final RphApiError rphApiError = new RphApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
      final WebRequest request) {
    log.info(ex.getClass().getName());
    final List<String> errors = new ArrayList<>();
    for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + "- "
          + violation.getMessage());
    }

    final RphApiError rphApiError = new RphApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), errors);
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  // 404

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    log.info(ex.getClass().getName());
    final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

    final RphApiError rphApiError = new RphApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),
        error);
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  // 405

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    log.info(ex.getClass().getName());
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    Objects.requireNonNull(ex.getSupportedHttpMethods())
        .forEach(t -> builder.append(t).append(" "));
    final RphApiError rphApiError = new RphApiError(HttpStatus.METHOD_NOT_ALLOWED,
        ex.getLocalizedMessage(), builder.toString());
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  // 415

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    log.info(ex.getClass().getName());
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(" "));

    final RphApiError rphApiError = new RphApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
        ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }

  // 500
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
    log.info(ex.getClass().getName());
    log.error("error", ex);
    final RphApiError rphApiError = new RphApiError(HttpStatus.INTERNAL_SERVER_ERROR,
        ex.getLocalizedMessage(), "error occurred");
    return new ResponseEntity<>(rphApiError, new HttpHeaders(), rphApiError.getStatus());
  }
}
