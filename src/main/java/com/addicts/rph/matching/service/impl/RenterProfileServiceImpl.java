package com.addicts.rph.matching.service.impl;

import com.addicts.rph.matching.dto.PageableImpl;
import com.addicts.rph.matching.dto.RenterProfileDto;
import com.addicts.rph.matching.entity.RenterProfile;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.exception.RphNotFoundException;
import com.addicts.rph.matching.repo.RenterProfileRepository;
import com.addicts.rph.matching.service.AuditService;
import com.addicts.rph.matching.service.RenterProfileService;
import com.addicts.rph.matching.util.ErrorMessages;
import com.addicts.rph.matching.util.PagedList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author sravantatikonda
 */
@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
@Data
public class RenterProfileServiceImpl implements RenterProfileService {

  @Autowired
  private RenterProfileRepository renterProfileRepository;

  @Autowired
  private MapperFacade mapperFacade;

  @Autowired
  private ErrorMessages errorMessages;

  @Autowired
  private AuditService auditService;

  /**
   * Creates a renter profile for a specific user
   *
   * @param renterProfileDto the value for {@link RenterProfileDto}
   * @return the processed value for {@link RenterProfileDto}
   */
  @Override
  public RenterProfileDto createRenterProfile(RenterProfileDto renterProfileDto)
      throws RphException {
    RenterProfile renterProfile = new RenterProfile();

    mapperFacade.map(renterProfileDto, renterProfile);

    renterProfile = renterProfileRepository.save(renterProfile);

    if (renterProfile.getRentalProfId() == null) {
      log.error("Error while inserting an renter profile record for user: {}",
          renterProfileDto.getUserId());
    }
    renterProfileDto.setRentalProfId(renterProfile.getRentalProfId());
    auditService
        .createAuditRecord(Long.valueOf(renterProfileDto.getRentalProfId()), "Renter Profile",
            "Renter profile successfully created");
    return renterProfileDto;
  }

  /**
   * Updates a renter profile for a specific user
   *
   * @param renterProfileDto the value for {@link RenterProfileDto}
   * @return the processed value for {@link RenterProfileDto}
   */
  @Override
  public RenterProfileDto updateRenterProfile(RenterProfileDto renterProfileDto)
      throws RphException {
    RenterProfile renterProfile = new RenterProfile();
    mapperFacade.map(renterProfileDto, renterProfile);
    renterProfileRepository.save(renterProfile);
    auditService
        .createAuditRecord(Long.valueOf(renterProfileDto.getRentalProfId()), "Renter Profile",
            "Renter profile successfully updated");
    return renterProfileDto;
  }

  /**
   * Deletes a renter profile for a specific user
   *
   * @param profileId the integer value for profile id
   * @return the true if deleted or false if unable to delete
   */
  @Override
  public Boolean deleteRenterProfile(Integer profileId)
      throws RphException {
    RenterProfileDto existing = getRenterProfile(profileId);

    renterProfileRepository.delete(mapperFacade.map(existing, RenterProfile.class));
    auditService
        .createAuditRecord(Long.valueOf(existing.getRentalProfId()), "Renter Profile",
            "Renter profile successfully deleted");
    return true;
  }

  /**
   * Returns a  {@link RenterProfileDto} renter profile record
   */
  @Override
  public RenterProfileDto getRenterProfile(Integer profileId) throws RphException {
    RenterProfile renterProfile;
    try {
      renterProfile = renterProfileRepository.getOne(profileId);
    } catch (NoResultException noUserException) {
      throw new RphNotFoundException(errorMessages.getErrorMessages().get("INVALID_PROFILE_ID"));
    }

    return mapperFacade.map(renterProfile, RenterProfileDto.class);
  }

  /**
   * Returns a list of all renter profile records
   *
   * @return the list of values for {@link RenterProfileDto}
   */
  @Override
  public PagedList<RenterProfileDto> getRenterProfiles(PageableImpl pageable) throws RphException {
    if (pageable.getPage() == null) {
      pageable.setPage(0);
    } else {
      pageable.setPage(pageable.getPage() - 1);
    }
    if (pageable.getSize() == null) {
      pageable.setSize(10);
    }
    Pageable pageable2 = PageRequest
        .of(pageable.getPage(), pageable.getSize(), PagedList.getSorts(pageable.getSort()));

    Page<RenterProfile> pagedList = renterProfileRepository.findAll(pageable2);
    List<RenterProfileDto> renterProfileDtos = mapperFacade
        .mapAsList(pagedList.getContent(), RenterProfileDto.class);
    return new PagedList<>(renterProfileDtos, pageable2, pagedList.getTotalElements());
  }
}
