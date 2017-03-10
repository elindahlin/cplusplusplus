package javabeat.net.springboot.gateway;

import javabeat.net.springboot.domain.Forecast;

public interface SmhiGateway {

	Forecast getForecast(double lat, double lon);
}
