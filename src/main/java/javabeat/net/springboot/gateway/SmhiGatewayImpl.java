package javabeat.net.springboot.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javabeat.net.springboot.domain.Forecast;

@Service
@Validated
public class SmhiGatewayImpl implements SmhiGateway {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmhiGatewayImpl.class);
	
	@Override
	public Forecast getForecast(double lat, double lon) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getUrl(lat, lon);
		LOGGER.info("Getting forecast from url: " + url);
		Forecast forecast = restTemplate.getForObject(url, Forecast.class);		
		return forecast;
	}

	
	private String getUrl(double lat, double lon) {
		return "http://opendata-download-metfcst.smhi.se/api/category/pmp2g/version/2/geotype/point/lon/" + lon + "/lat/" + lat + "/data.json";
	}
}
