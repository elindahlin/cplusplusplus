package javabeat.net.springboot.service;

import javabeat.net.springboot.domain.Weather;

public interface WeatherService {

	Weather getWeather(double lat, double lon);
	
}
