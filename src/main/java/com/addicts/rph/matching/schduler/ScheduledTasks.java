/*
package com.addicts.rph.matching.schduler;

import com.addicts.rph.matching.dto.PageableImpl;
import com.addicts.rph.matching.dto.ProfessionalRequestDto;
import com.addicts.rph.matching.dto.RentalPropertyListDto;
import com.addicts.rph.matching.dto.SavedSearchDto;
import com.addicts.rph.matching.dto.UserDto;
import com.addicts.rph.matching.enums.ProfRequestStatusType;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.service.EmailService;
import com.addicts.rph.matching.service.ProfessionalsService;
import com.addicts.rph.matching.service.RentalPropertyService;
import com.addicts.rph.matching.service.SavedSearchService;
import com.addicts.rph.matching.service.UserService;
import com.addicts.rph.matching.util.PagedList;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

*/
/**
 * @author sravantatikonda
 *//*

@Component
@Slf4j
public class ScheduledTasks {


  @Autowired
  private EmailService emailService;

  @Autowired
  private UserService userService;

  //@Scheduled(cron = "${scheduled.cron.savedSearchDaily}")
  public void scheduleSavedSearchUsersNotify() throws RphException {
    log.info("Saved search notification started at {} for daily consumers.", ZonedDateTime.now());
    List<SavedSearchDto> dailySavedSearches = savedSearchService.getDailySavedSearches();
    for (SavedSearchDto dto : dailySavedSearches) {
      UserDto userDto = userService.getUser(dto.getUserId());
      PageableImpl pageable = new PageableImpl(1, Integer.MAX_VALUE, null);
      PagedList<RentalPropertyListDto> pagedList =
          rentalPropertyService.getAllRentalProperties(pageable, dto.getFilterBody());
      String listingIds = StringUtils
          .collectionToCommaDelimitedString(pagedList.getRecords().stream()
              .map(RentalPropertyListDto::getListingId)
              .collect(Collectors.toList()));
      emailService
          .sendTextEmail(userDto.getEmail(), "Your saved search results for today", listingIds);
      log.info("Saved search notifications sent for user id {} and user name {}  started at {}.",
          userDto.getUserId(), userDto.getEmail(), ZonedDateTime.now());
    }
    log.info("Saved search notification ended at {} for daily consumers.", ZonedDateTime.now());
  }

  //@Scheduled(cron = "${scheduled.cron.serviceReminder}")
  //TODO: Run at different timings on weekdays and weekends
  public void scheduleAppointmentReminders() throws RphException {
    log.info("Professional Search Appointment reminders notification started at {}.",
        ZonedDateTime.now());
    PageableImpl pageable = new PageableImpl(1, Integer.MAX_VALUE, null);
    ProfessionalRequestDto requestDto = new ProfessionalRequestDto();
    requestDto.setRequestStatus(ProfRequestStatusType.Accepted);
    // Just filter based on date but not with time.
    requestDto.setRequestedOn(ZonedDateTime.now());
    PagedList<ProfessionalRequestDto> professionalRequestDtos = professionalsService
        .getAllProfRequests(requestDto, pageable);
    for (ProfessionalRequestDto dto : professionalRequestDtos.getRecords()) {
      UserDto userDto = userService.getUser(dto.getProfessionalUserId());
      String body =
          "Appointment has been scheduled for " + dto.getReqSpeciality()
              + " at the residence Address:"
              + dto.getRequestLocation().getAddress1() + ", " + dto.getRequestLocation()
              .getAddress1()
              + ", " + dto.getRequestLocation().getCity() + ", " + dto.getRequestLocation()
              .getState() + ", "
              + dto.getRequestLocation().getZipCode();
      emailService
          .sendTextEmail(userDto.getEmail(), "Reminder for an service request appointment", body);
      log.info(
          "Professional appointment request notification sent for user id {} and user name {}  started at {}.",
          userDto.getUserId(), userDto.getEmail(), ZonedDateTime.now());
    }
    log.info("Saved search notification ended at {} for daily consumers.", ZonedDateTime.now());
  }


  //@Scheduled(cron = "${scheduled.cron.archiveReportedListings}")
  public void scheduleArchivalOfReportedListings() throws RphException {
    */
/*
   TODO: If a listing is reported by > 5 users, change the status of the listing to archive and move it to archive table.

    log.info("Archiving listings which are reported as scam started at {}.", ZonedDateTime.now());
    List<SavedSearchDto> dailySavedSearches = savedSearchService.getDailySavedSearches();
    for (SavedSearchDto dto : dailySavedSearches) {
      UserDto userDto = userService.getUser(dto.getUserId());
      PageableImpl pageable = new PageableImpl(1, Integer.MAX_VALUE, null);
      PagedList<RentalPropertyListDto> pagedList = rentalPropertyService
          .getReportedListings(pageable);
      String listingIds = StringUtils
          .collectionToCommaDelimitedString(pagedList.getRecords().stream()
              .map(RentalPropertyListDto::getListingId)
              .collect(Collectors.toList()));
      emailService
          .sendTextEmail(userDto.getEmail(), "Your saved search results for today", listingIds);
      log.info("Saved search notifications sent for user id {} and user name {}  started at {}.",
          userDto.getUserId(), userDto.getEmail(), ZonedDateTime.now());
    }
    log.info("Saved search notification ended at {} for daily consumers.", ZonedDateTime.now());*//*

  }
}
*/
