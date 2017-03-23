package javabeat.net.springboot.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javabeat.net.springboot.domain.Forecast;
import javabeat.net.springboot.domain.ForecastEvent;
import javabeat.net.springboot.domain.Parameter;
import javabeat.net.springboot.domain.Weather;
import javabeat.net.springboot.gateway.SmhiGateway;

public class WeatherServiceTest {

	private WeatherService weatherService;
	private SmhiGateway smhiGatewayMock;
	
	@Before
	public void setup() {
		smhiGatewayMock = Mockito.mock(SmhiGateway.class);
		weatherService = new WeatherServiceImpl(smhiGatewayMock);
	}
	
	@Test
	public void testExpectedWeatherFound() {
		when(smhiGatewayMock.getForecast(anyLong(), anyLong())).thenReturn(createExampleForecast());
		
		Weather weather = weatherService.getWeather(59.18, 18.23);
		assertNotNull(weather);
		assertEquals(weather.getPrecipitationType().getValue(), 1);
		assertEquals(weather.getWeatherType().getValue(), 2);
		assertEquals(weather.getTemperature(), 3, 0.01);
	}
	
//	@Test
//	public void testFail() {
//		assertNotNull(null);
//	}

	private Forecast createExampleForecast() {
		Forecast forecast = new Forecast();
		forecast.setTimeSeries(createForecastEvents());
		return forecast;
	}

	private ForecastEvent[] createForecastEvents() {
		ForecastEvent[] events = new ForecastEvent[3];
		ForecastEvent event0 = new ForecastEvent();
		event0.setValidTime(ZonedDateTime.now().minusMinutes(75).toString());
		events[0] = event0;
		ForecastEvent event1 = new ForecastEvent();
		event1.setValidTime(ZonedDateTime.now().minusMinutes(15).toString());
		Parameter[] parameters = { new Parameter("pcat", 1), 
								   new Parameter("Wsymb", 2), 
								   new Parameter("t", 3) };
		event1.setParameters(parameters);
		events[1] = event1;
		ForecastEvent event2 = new ForecastEvent();
		event2.setValidTime(ZonedDateTime.now().plusMinutes(45).toString());
		events[2] = event2;
		return events;
	}
}
