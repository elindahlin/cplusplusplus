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

import javabeat.net.springboot.domain.AddressComponent;
import javabeat.net.springboot.domain.City;
import javabeat.net.springboot.domain.Location;
import javabeat.net.springboot.domain.LocationSearchResult;
import javabeat.net.springboot.domain.Option;
import javabeat.net.springboot.domain.PlaceSearchResult;

@Service
@Validated
public class GoogleGatewayImpl implements GoogleGateway {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleGatewayImpl.class);
	private final RestTemplate restTemplate;
	
	private final String API_KEY = "AIzaSyDvzJrzaCTEM58N3DaFc6bqnWrKnBZcgIc";
	private final String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
	private final String LOCATION_URL = "https://maps.googleapis.com/maps/api/geocode/json?";

	public GoogleGatewayImpl() {
		this.restTemplate = new RestTemplate();
	}
	
	@Override
	public Collection<Option> findOptionsByType(String type, double lat, double lon, double radius) {
		String url = getTypeUrl(type, lat, lon, radius);
		PlaceSearchResult searchResult = getPlaceInfo(url);		
		return searchResult.getStatus().equals("OK") ? convertToOptionList(searchResult) : Collections.emptyList();
	}
	
	@Override
	public Collection<Option> findOptionsByText(String text, double lat, double lon, double radius) {
		String url = getTextUrl(text, lat, lon, radius);
		PlaceSearchResult searchResult = getPlaceInfo(url);		
		return searchResult.getStatus().equals("OK") ? convertToOptionList(searchResult) : Collections.emptyList();
	}

	private PlaceSearchResult getPlaceInfo(String url) {
		LOGGER.info("Getting options by type from url: " + url);
		return restTemplate.getForObject(url, PlaceSearchResult.class);
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
	
	private String getLocationNameUrl(double lat, double lon) {
		return LOCATION_URL + "latlng=" + lat + "," + lon;
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

	@Override
	public City getLocationName(double lat, double lon) {
		RestTemplate restTemplate = new RestTemplate();
		String url = getLocationNameUrl(lat, lon);
		LOGGER.info("Getting location by string from url: " + url);
		LocationSearchResult searchResult = restTemplate.getForObject(url, LocationSearchResult.class);		
		return searchResult.getStatus().equals("OK") ? getCityName(searchResult) : null;
	}

	private City getCityName(LocationSearchResult searchResult) {
		AddressComponent[] addressComponents = searchResult.getResults()[0].getAddress_components();
		for (int i = 0; i < addressComponents.length; i++) {
			AddressComponent component = addressComponents[i];
			if (Arrays.asList(component.getTypes()).contains("postal_town")) {
				City city = new City();
				city.setName(component.getShort_name());
				return city;
			}
		}
		return null;
	}
}
