package javabeat.net.springboot.gateway;

import java.util.Collection;

import javabeat.net.springboot.domain.Option;
import javabeat.net.springboot.domain.PlaceSearchResult;

public interface GoogleGateway {

	Collection<Option> findOptionsByType(String type, double lat, double lon, double radius);
	
	Collection<Option> findOptionsByText(String text, double lat, double lon, double radius);
}
