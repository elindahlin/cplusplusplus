package javabeat.net.springboot.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.Activity;
import javabeat.net.springboot.domain.ActivityType;
import javabeat.net.springboot.service.ActivityService;

@RestController
public class ActivityController {
	
	private ActivityService activityService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
	
	@Inject
	public ActivityController(final ActivityService activityService) {
		this.activityService = activityService;
	}
	
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
    public Collection<Activity> requestData(@RequestParam(value = "lat", required = true) double lat,
    		@RequestParam(value = "lon", required = true) double lon,
    		@RequestParam(value = "rangeKm", required = true) int rangeKm,
    		@RequestParam(value = "dateTime", required = true) String dateTime,
    		@RequestParam(value = "pricePerPerson", required = true) int pricePerPerson,
    		@RequestParam(value = "nbrOfPersons", required = true) int nbrOfPersons,
    		@RequestParam(value = "activityType", required = true) String activityType) {
		
		try {			
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime);
			ActivityType activity = ActivityType.valueOf(activityType.toUpperCase());
			
			LOGGER.info("ManagedParsing");
			return activityService.findActivities(lat, lon, rangeKm, zonedDateTime, pricePerPerson, 
					nbrOfPersons, activity);
		} catch (DateTimeParseException | NumberFormatException e) {
			LOGGER.error("Error parsing: ", e.getMessage());
		} 
        return Collections.emptyList();
    }

}
