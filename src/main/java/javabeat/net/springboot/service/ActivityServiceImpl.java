package javabeat.net.springboot.service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javabeat.net.springboot.domain.Activity;
import javabeat.net.springboot.domain.ActivityType;

@Service
@Validated
public class ActivityServiceImpl implements ActivityService {

	@Override
	public Collection<Activity> findActivities(double lat, double lon, int rangeKm, ZonedDateTime dateTime,
			int pricePerPerson, int nbrOfPersons, ActivityType activityType) {
		// TODO Auto-generated method stub
		
		
		return Collections.emptyList();
	}

}
