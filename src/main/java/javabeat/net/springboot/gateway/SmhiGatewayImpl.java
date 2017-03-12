package javabeat.net.springboot.gateway;

import java.util.Locale;

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
		String latitude = String.format(Locale.US, "%.4f", lat);
		String longitude = String.format(Locale.US, "%.4f", lon);
		RestTemplate restTemplate = new RestTemplate();
		String url = getUrl(latitude, longitude);
		LOGGER.info("Getting forecast from url: " + url);
		Forecast forecast = restTemplate.getForObject(url, Forecast.class);		
		return forecast;
	}

	
	private String getUrl(String lat, String lon) {
		return "http://opendata-download-metfcst.smhi.se/api/category/pmp2g/version/2/geotype/point/lon/" + lon + "/lat/" + lat + "/data.json";
	}
}
