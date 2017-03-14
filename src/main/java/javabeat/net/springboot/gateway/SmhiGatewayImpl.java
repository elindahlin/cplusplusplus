package javabeat.net.springboot.gateway;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
//import org.springframework.boot.web.client.RestTemplateBuilder;

import javabeat.net.springboot.domain.Forecast;

@Service
@Validated
public class SmhiGatewayImpl implements SmhiGateway {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmhiGatewayImpl.class);
	private final RestTemplate restTemplate;
	
	public SmhiGatewayImpl() { //RestTemplateBuilder restTemplateBuilder) {
        //this.restTemplate = restTemplateBuilder.build();
		this.restTemplate = new RestTemplate();
    }
	
	@Override
	public Forecast getForecast(double lat, double lon) {
		String latitude = String.format(Locale.US, "%.4f", lat);
		String longitude = String.format(Locale.US, "%.4f", lon);
		Forecast forecast = getForecast(latitude, longitude);		
		return forecast;
	}

	private Forecast getForecast(String lat, String lon) {
		String url = getUrl(lat, lon);
		LOGGER.info("Getting forecast from url: " + url);
		return restTemplate.getForObject(url, Forecast.class);
	}

	
	private String getUrl(String lat, String lon) {
		return "http://opendata-download-metfcst.smhi.se/api/category/pmp2g/version/2/geotype/point/lon/" + lon + "/lat/" + lat + "/data.json";
	}
}
