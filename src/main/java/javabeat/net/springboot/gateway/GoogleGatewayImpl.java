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

import javabeat.net.springboot.domain.Location;
import javabeat.net.springboot.domain.LocationSearchResult;
import javabeat.net.springboot.domain.Option;
import javabeat.net.springboot.domain.PlaceSearchResult;

@Service
@Validated
public class GoogleGatewayImpl implements GoogleGateway {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleGatewayImpl.class);
	
	private final String API_KEY = "AIzaSyDvzJrzaCTEM58N3DaFc6bqnWrKnBZcgIc";
	private final String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
	private final String LOCATION_URL = "https://maps.googleapis.com/maps/api/geocode/json?";

	@Override
	public Collection<Option> findOptionsByType(String type, double lat, double lon, double radius) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getTypeUrl(type, lat, lon, radius);
		LOGGER.info("Getting options by type from url: " + url);
		PlaceSearchResult searchResult = restTemplate.getForObject(url, PlaceSearchResult.class);		
		return searchResult.getStatus().equals("OK") ? convertToOptionList(searchResult) : Collections.emptyList();
	}
	
	@Override
	public Collection<Option> findOptionsByText(String text, double lat, double lon, double radius) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getTextUrl(text, lat, lon, radius);
		LOGGER.info("Getting options by text from url: " + url);
		PlaceSearchResult searchResult = restTemplate.getForObject(url, PlaceSearchResult.class);		
		return searchResult.getStatus().equals("OK") ? convertToOptionList(searchResult) : Collections.emptyList();
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
	
	private String getLocationUrl(String locationString) {
		return LOCATION_URL + "address=" + locationString;
	}

	@Override
	public Location getLocation(String locationString) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getLocationUrl(locationString);
		LOGGER.info("Getting location by string from url: " + url);
		LocationSearchResult searchResult = restTemplate.getForObject(url, LocationSearchResult.class);		
		return searchResult.getStatus().equals("OK") ? 
				searchResult.getResults()[0].getGeometry().getLocation() : null;
	}
}
