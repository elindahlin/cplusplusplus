package javabeat.net.springboot.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.Activity;
import javabeat.net.springboot.domain.ActivityType;
import javabeat.net.springboot.domain.WeatherType;
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
    public Collection<Activity> getActivitySuggestions(
    		@RequestParam(value = "lat", required = true) double lat,
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

	@RequestMapping(value = "/dbAddActivity", method = RequestMethod.GET)
	public Activity addActivity(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "price", required = true) int price,
			@RequestParam(value = "minPersons", required = true) int minPersons,
			@RequestParam(value = "maxPersons", required = true) int maxPersons,
			@RequestParam(value = "latitude", required = true) double latitude,
			@RequestParam(value = "longitude", required = true) double longitude,
			@RequestParam(value = "training", required = false) boolean training,
			@RequestParam(value = "social", required = false) boolean social,
			@RequestParam(value = "relaxed", required = false) boolean relaxed,
			@RequestParam(value = "family", required = false) boolean family,
			@RequestParam(value = "cultural", required = false) boolean cultural,
			@RequestParam(value = "date", required = false) boolean date,
			@RequestParam(value = "clearSky", required = false) boolean clearSky,
			@RequestParam(value = "nearlyClearSky", required = false) boolean nearlyClearSky,
			@RequestParam(value = "variableCloudiness", required = false) boolean variableCloudiness,
			@RequestParam(value = "halfClearSky", required = false) boolean halfClearSky,
			@RequestParam(value = "cloudySky", required = false) boolean cloudySky,
			@RequestParam(value = "overcast", required = false) boolean overcast,
			@RequestParam(value = "fog", required = false) boolean fog,
			@RequestParam(value = "rainShowers", required = false) boolean rainShowers,
			@RequestParam(value = "thunderstorm", required = false) boolean thunderstorm,
			@RequestParam(value = "lightSleet", required = false) boolean lightSleet,
			@RequestParam(value = "snowShowers", required = false) boolean snowShowers,
			@RequestParam(value = "rain", required = false) boolean rain,
			@RequestParam(value = "thunder", required = false) boolean thunder,
			@RequestParam(value = "sleet", required = false) boolean sleet,
			@RequestParam(value = "snowfall", required = false) boolean snowfall) {
		
		Map<ActivityType, Boolean> suitableActivityTypes = putActivitiesInMap(training, social, relaxed, 
				family, cultural, date);
		Map<WeatherType, Boolean> suitableWeatherTypes = putWeathersInMap(clearSky, nearlyClearSky,
				variableCloudiness, halfClearSky, cloudySky, overcast, fog, rainShowers, thunderstorm,
				lightSleet, snowShowers, rain, thunder, sleet, snowfall);
		
		Activity activity = new Activity(name, description, url, price, minPersons, maxPersons, latitude,
				longitude, suitableActivityTypes, suitableWeatherTypes);
		return activityService.save(activity);
	}
	
	private Map<WeatherType, Boolean> putWeathersInMap(boolean clearSky, boolean nearlyClearSky,
			boolean variableCloudiness, boolean halfClearSky, boolean cloudySky, boolean overcast, boolean fog,
			boolean rainShowers, boolean thunderstorm, boolean lightSleet, boolean snowShowers, boolean rain,
			boolean thunder, boolean sleet, boolean snowfall) {
		Map<WeatherType, Boolean> weatherTypes = new HashMap<>();
		weatherTypes.put(WeatherType.CLEAR_SKY, clearSky);
		weatherTypes.put(WeatherType.NEARLY_CLEAR_SKY, nearlyClearSky);
		weatherTypes.put(WeatherType.VARIABLE_CLOUDINESS, variableCloudiness);
		weatherTypes.put(WeatherType.HALF_CLEAR_SKY, halfClearSky);
		weatherTypes.put(WeatherType.CLOUDY_SKY, cloudySky);
		weatherTypes.put(WeatherType.OVERCAST, overcast);
		weatherTypes.put(WeatherType.FOG, fog);
		weatherTypes.put(WeatherType.RAIN_SHOWERS, rainShowers);
		weatherTypes.put(WeatherType.THUNDERSTORM, thunderstorm);
		weatherTypes.put(WeatherType.LIGHT_SLEET, lightSleet);
		weatherTypes.put(WeatherType.SNOW_SHOWERS, snowShowers);
		weatherTypes.put(WeatherType.RAIN, rain);
		weatherTypes.put(WeatherType.THUNDER, thunder);
		weatherTypes.put(WeatherType.SLEET, sleet);
		weatherTypes.put(WeatherType.SNOWFALL, snowfall);
		return weatherTypes;
	}

	private Map<ActivityType, Boolean> putActivitiesInMap(boolean training, boolean social, boolean relaxed,
			boolean family, boolean cultural, boolean date) {
		Map<ActivityType, Boolean> activityTypes = new HashMap<>();
		activityTypes.put(ActivityType.TRAINING, training);
		activityTypes.put(ActivityType.SOCIAL, social);
		activityTypes.put(ActivityType.RELAXED, relaxed);
		activityTypes.put(ActivityType.FAMILY, family);
		activityTypes.put(ActivityType.CULTURAL, cultural);
		activityTypes.put(ActivityType.DATE, date);
		return activityTypes;
	}

	@RequestMapping(value = "/dbGetActivity", method = RequestMethod.GET)
	public Collection<Activity> getActivities() {
		return activityService.getActivityList();
	}
	
}
