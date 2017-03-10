package javabeat.net.springboot.service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javabeat.net.springboot.domain.Activity;
import javabeat.net.springboot.domain.ActivityType;
import javabeat.net.springboot.domain.Weather;
import javabeat.net.springboot.repository.ActivityRepository;

@Service
@Validated
public class ActivityServiceImpl implements ActivityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);
	private final WeatherService weatherService;
	private final ActivityRepository activityRepository;
	
	@Inject
    public ActivityServiceImpl(final WeatherService weatherService, final ActivityRepository activityRepository) {
		this.weatherService = weatherService;
		this.activityRepository = activityRepository;
    }
	
	@Override
	public Collection<Activity> findActivities(double lat, double lon, int rangeKm, ZonedDateTime dateTime,
			int pricePerPerson, int nbrOfPersons, ActivityType activityType) {
		Weather weather = weatherService.getWeatherForSpecifiedTime(lat, lon, dateTime);
		List<Activity> activities = activityRepository.findAll();
		
		activities = activities.stream()
			.filter(actType -> actType.isSuitableFor(activityType))
			.filter(actWeather -> actWeather.isSuitableFor(weather.getWeatherType()))
			.filter(actPrice -> actPrice.getPrice() <= pricePerPerson)
			.filter(actPersMax -> actPersMax.getMaxPersons() >= nbrOfPersons)
			.filter(actPersMin -> actPersMin.getMinPersons() <= nbrOfPersons)
			.filter(actPos -> actPos.isCloseTo(lat, lon, rangeKm))
			.collect(Collectors.toList());
		
		return activities;
	}

	@Override
	@Transactional
	public Activity save(@NotNull @Valid final Activity activity) {
		LOGGER.debug("Creating {}", activity);
        return activityRepository.save(activity);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Activity> getActivityList() {
		LOGGER.info("Getting all activities");
		return activityRepository.findAll();
	}

}
