package com.addicts.rph.matching.service;

import com.addicts.rph.matching.dto.PageableImpl;
import com.addicts.rph.matching.dto.RenterProfileDto;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.util.PagedList;

/**
 * @author sravantatikonda
 */
public interface RenterProfileService {

  /**
   * Creates a renter profile for a specific user
   *
   * @param renterProfileDto the value for {@link RenterProfileDto}
   * @return the processed value for {@link RenterProfileDto}
   */
  RenterProfileDto createRenterProfile(RenterProfileDto renterProfileDto) throws RphException;

  /**
   * Returns a list of all renter profile records
   *
   * @return the list of values for {@link RenterProfileDto}
   */
  PagedList<RenterProfileDto> getRenterProfiles(PageableImpl pageable) throws RphException;

  /**
   * Returns a  {@link RenterProfileDto} renter profile record
   */
  RenterProfileDto getRenterProfile(Integer profileId) throws RphException;

  /**
   * Deletes a renter profile for a specific user
   *
   * @param profileId the integer value for profile id
   * @return the true if deleted or false if unable to delete
   */
  Boolean deleteRenterProfile(Integer profileId) throws RphException;

  /**
   * Updates a renter profile for a specific user
   *
   * @param renterProfileDto the value for {@link RenterProfileDto}
   * @return the processed value for {@link RenterProfileDto}
   */
  RenterProfileDto updateRenterProfile(RenterProfileDto renterProfileDto) throws RphException;
}
