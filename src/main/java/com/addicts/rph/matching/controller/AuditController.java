package com.addicts.rph.matching.controller;

import com.addicts.rph.matching.dto.AuditDto;
import com.addicts.rph.matching.dto.AuditListRequestDto;
import com.addicts.rph.matching.dto.PageableImpl;
import com.addicts.rph.matching.dto.Response;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.service.AuditService;
import com.addicts.rph.matching.util.PagedList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sravantatikonda
 */
@RestController
@Api(value = "Audit",
    description = "This controller contains endpoints for retrieving audit records of a specific user")
@RequestMapping("/internal/audit/")
public class AuditController {

  @Autowired
  private AuditService auditService;

  /**
   * Returns a list of all audit records for a specific user
   *
   * @param userId the identifier of the requesting user
   * @return the list of values for {@link AuditDto}
   */
  @ApiOperation(value = "getAuditRecords", notes = "This API will return all audit records for a specific user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @GetMapping(value = "/user/{user-id}/", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<List<AuditDto>> getAuditRecords(@PathVariable("user-id") Integer userId)
      throws RphException {

    return new Response<>(auditService.getAuditRecords(userId));
  }

  /**
   * Returns a list of audit records of all the users for admin auditing
   *
   * @return the list of values for {@link AuditDto}
   */
  @ApiOperation(value = "getAllAuditRecords", notes = "This API will return all audit records of all users")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<PagedList<AuditDto>> getAllAuditRecords(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size, @RequestParam(required = false) String[] sort,
      @RequestBody AuditListRequestDto auditListRequestDto)
      throws RphException {
    PageableImpl pageable = new PageableImpl(page, size, sort);
    return new Response<>(auditService.getAllAuditRecords(pageable, auditListRequestDto));
  }
}
