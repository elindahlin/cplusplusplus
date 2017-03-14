package javabeat.net.springboot.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.Weather;
import javabeat.net.springboot.domain.WeatherType;
import javabeat.net.springboot.service.WeatherService;

@RestController
public class WeatherController {

	private final WeatherService weatherService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
	
	@Inject
	public WeatherController(final WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
    public @ResponseBody Weather requestData(@RequestParam(value = "lat", required = true) double lat,
    		@RequestParam(value = "lon", required = true) double lon) {
        Weather weather = weatherService.getWeather(lat, lon);
        LOGGER.info("Current weather is: " + weather.getWeatherType() + "@(" + lat + "," + lon + ")");
        return weather;
    }
	
	@RequestMapping(value = "/weatherType", method = RequestMethod.GET)
	public @ResponseBody WeatherType[] getWeatherTypes() {
		return WeatherType.values();
	}
}
