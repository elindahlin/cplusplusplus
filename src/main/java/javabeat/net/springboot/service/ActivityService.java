package javabeat.net.springboot.service;

import java.time.ZonedDateTime;
import java.util.List;

import javabeat.net.springboot.domain.Activity;
import javabeat.net.springboot.domain.ActivityType;

public interface ActivityService {
	
	Activity save(Activity activity);
	
//	void removeActivityFromDb(Activity activity);
	
	List<Activity> getActivityList();
	
	List<Activity> findActivities(double lat, double lon, int rangeKm, ZonedDateTime dateTime, 
			int pricePerPerson, int nbrOfPersons, ActivityType activityType);

}
