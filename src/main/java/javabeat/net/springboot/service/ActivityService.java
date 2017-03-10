package javabeat.net.springboot.service;

import java.time.ZonedDateTime;
import java.util.Collection;

import javabeat.net.springboot.domain.Activity;
import javabeat.net.springboot.domain.ActivityType;

public interface ActivityService {
	
	Activity save(Activity activity);
	
//	void removeActivityFromDb(Activity activity);
	
	Collection<Activity> getActivityList();
	
	Collection<Activity> findActivities(double lat, double lon, int rangeKm, ZonedDateTime dateTime, 
			int pricePerPerson, int nbrOfPersons, ActivityType activityType);

}
