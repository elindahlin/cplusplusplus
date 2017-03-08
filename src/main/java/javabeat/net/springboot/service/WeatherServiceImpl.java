package javabeat.net.springboot.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javabeat.net.springboot.domain.Forecast;
import javabeat.net.springboot.domain.ForecastEvent;
import javabeat.net.springboot.domain.Parameter;
import javabeat.net.springboot.domain.Weather;
import javabeat.net.springboot.domain.WeatherType;
import javabeat.net.springboot.gateway.SmhiGateway;

@Service
@Validated
public class WeatherServiceImpl implements WeatherService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);
	private final SmhiGateway smhiGateway;
	
	@Inject
    public WeatherServiceImpl(final SmhiGateway smhiGateway) {
		this.smhiGateway = smhiGateway;
    }
	
	
	@Override
	public Weather getWeather(double lat, double lon) {
		Forecast forecast = smhiGateway.getForecast(lat, lon);
		ForecastEvent forecastEvent = getClosestForecastEvent(forecast);
		if (forecastEvent == null) {
			LOGGER.warn("Could not find valid forecast");
			throw new IllegalArgumentException("Could not find valid forecast");
		}
		double temperature = getTemperature(forecastEvent);
		WeatherType weatherType = getWeatherType(forecastEvent);
		
		Weather weather = new Weather();
		weather.setDateTime(ZonedDateTime.now().toString());
		weather.setTemperature(temperature);
		weather.setWeatherType(weatherType);
		return weather;
	}


	private WeatherType getWeatherType(ForecastEvent forecastEvent) {
		List<Parameter> parameters = Arrays.asList(forecastEvent.getParameters());
		Optional<Number> symbolValue = parameters.stream()
				.filter(parameter -> parameter.getName().equals("Wsymb"))
				.map(symbolParameter -> symbolParameter.getValues()[0])
				.findFirst();
		return symbolValue.isPresent() ? WeatherType.getWeatherType(symbolValue.get().intValue()) : null;
	}


	private double getTemperature(ForecastEvent forecastEvent) {
		List<Parameter> parameters = Arrays.asList(forecastEvent.getParameters());
		Optional<Number> temperature = parameters.stream()
				.filter(parameter -> parameter.getName().equals("t"))
				.map(tempParameter -> tempParameter.getValues()[0])
				.findFirst();
		return temperature.isPresent() ? temperature.get().doubleValue() : null;
	}


	private ForecastEvent getClosestForecastEvent(Forecast forecast) {
		ZonedDateTime now = ZonedDateTime.now();

		ForecastEvent[] events = forecast.getTimeSeries();
		for (int i = 1; i < events.length; i++) {
			ZonedDateTime past = ZonedDateTime.parse(events[i-1].getValidTime());
			ZonedDateTime next = ZonedDateTime.parse(events[i].getValidTime());
			if (now.isAfter(past) && now.isBefore(next)) {
				Duration d1 = Duration.between(past, now);
				Duration d2 = Duration.between(now, next);
				return d1.compareTo(d2) > 0 ? events[i] : events[i-1];
			}
		}
		return null;
	}

}
