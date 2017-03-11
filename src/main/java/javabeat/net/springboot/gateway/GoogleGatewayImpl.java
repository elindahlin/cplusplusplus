package javabeat.net.springboot.gateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javabeat.net.springboot.domain.Option;
import javabeat.net.springboot.domain.PlaceSearchObject;
import javabeat.net.springboot.domain.PlaceSearchResult;

@Service
@Validated
public class GoogleGatewayImpl implements GoogleGateway {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleGatewayImpl.class);
	
	private final String API_KEY = "AIzaSyDvzJrzaCTEM58N3DaFc6bqnWrKnBZcgIc";
	private final String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

	@Override
	public Collection<Option> findOptionsByType(String type, double lat, double lon, double radius) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getTypeUrl(type, lat, lon, radius);
		LOGGER.info("Getting options by type from url: " + url);
		PlaceSearchResult searchResult = restTemplate.getForObject(url, PlaceSearchResult.class);		
		return convertToOptionList(searchResult);
	}
	
	@Override
	public Collection<Option> findOptionsByText(String text, double lat, double lon, double radius) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getTextUrl(text, lat, lon, radius);
		LOGGER.info("Getting options by text from url: " + url);
		PlaceSearchResult searchResult = restTemplate.getForObject(url, PlaceSearchResult.class);		
		return convertToOptionList(searchResult);
	}
	
	private Collection<Option> convertToOptionList(PlaceSearchResult searchResult) {
		if (searchResult.getResults() == null) {
			return Collections.emptyList();
		}
		Collection<Option> options = new ArrayList<>();
		Arrays.asList(searchResult.getResults()).forEach(object -> {
			Option option = new Option();
			option.setGeometry(object.getGeometry());
			option.setIcon(object.getIcon());
			option.setName(object.getName());
			option.setOpeningHours(object.getOpeningHours());
			option.setTypes(object.getTypes());
			option.setVicinity(object.getVicinity());
			options.add(option);
		});
		return options;
	}

	private String getTypeUrl(String keyword, double lat, double lon, double radius) {
		return URL + "key=" + API_KEY + "&type=" + keyword + "&location=" + lat + "," + lon + "&radius=" + radius;
	}
	
	private String getTextUrl(String text, double lat, double lon, double radius) {
		return URL + "key=" + API_KEY + "&keyword=" + text + "&location=" + lat + "," + lon + "&radius=" + radius;
	}
}
