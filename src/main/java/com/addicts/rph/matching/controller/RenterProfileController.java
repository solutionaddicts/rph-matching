package com.addicts.rph.matching.controller;

import com.addicts.rph.matching.dto.PageableImpl;
import com.addicts.rph.matching.dto.RenterProfileDto;
import com.addicts.rph.matching.dto.Response;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.service.RenterProfileService;
import com.addicts.rph.matching.util.PagedList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sravantatikonda
 */
@RestController
@Api(value = "Audit",
    description = "This controller contains endpoints for managing a renter's profile")
@RequestMapping("sec/renter-profile/")
public class RenterProfileController {

  @Autowired
  private RenterProfileService renterProfileService;

  /**
   * Returns a list of all renter profile records
   *
   * @return the list of values for {@link RenterProfileDto}
   */
  @ApiOperation(value = "getAllProfiles", notes = "This API will return all renter profiles")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @GetMapping(value = "internal/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<PagedList<RenterProfileDto>> getAllProfiles(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size, @RequestParam(required = false) String[] sort)
      throws RphException {
    PageableImpl pageable = new PageableImpl(page, size, sort);

    return new Response<>(renterProfileService.getRenterProfiles(pageable));
  }

  /**
   * Returns a  {@link RenterProfileDto} renter profile record
   */
  @ApiOperation(value = "getRentalProfile", notes = "This API will return a renter profile for a specific user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @GetMapping(value = "/{profileId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<RenterProfileDto> getRentalProfile(@PathVariable Integer profileId)
      throws RphException {
    return new Response<>(renterProfileService.getRenterProfile(profileId));
  }

  /**
   * Creates a renter profile for a specific user
   *
   * @param renterProfileDto the value for {@link RenterProfileDto}
   * @return the processed value for {@link RenterProfileDto}
   */
  @ApiOperation(value = "createRentalProfile", notes = "This API will creates a renter profile for a specific user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<RenterProfileDto> createRentalProfile(
      @RequestBody RenterProfileDto renterProfileDto)
      throws RphException {
    return new Response<>(renterProfileService.createRenterProfile(renterProfileDto));
  }

  /**
   * Updates a renter profile for a specific user
   *
   * @param renterProfileDto the value for {@link RenterProfileDto}
   * @return the processed value for {@link RenterProfileDto}
   */
  @ApiOperation(value = "updateRentalProfile", notes = "This API updates a renter profile of a specific user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<RenterProfileDto> updateRentalProfile(
      @RequestBody RenterProfileDto renterProfileDto)
      throws RphException {
    return new Response<>(renterProfileService.updateRenterProfile(renterProfileDto));
  }

  /**
   * Deletes a renter profile for a specific user
   *
   * @param profileId the integer value for profile id
   * @return the true if deleted or false if unable to delete
   */
  @ApiOperation(value = "deleteRentalProfile", notes = "This API deletes a renter profile of a specific user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 428, message = "Precondition Required")
  })
  @DeleteMapping(value = "/{profileId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<Boolean> deleteRentalProfile(@PathVariable Integer profileId)
      throws RphException {
    return new Response<>(renterProfileService.deleteRenterProfile(profileId));
  }


}
