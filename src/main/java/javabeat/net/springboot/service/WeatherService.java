package javabeat.net.springboot.service;

import java.time.ZonedDateTime;

import javabeat.net.springboot.domain.Weather;

public interface WeatherService {

	Weather getWeather(double lat, double lon);
	
	Weather getWeatherForSpecifiedTime(double lat, double lon, ZonedDateTime dateTime);
	
}
