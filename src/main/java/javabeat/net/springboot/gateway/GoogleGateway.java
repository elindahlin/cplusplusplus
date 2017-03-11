package javabeat.net.springboot.gateway;

import java.util.Collection;

import javabeat.net.springboot.domain.Location;
import javabeat.net.springboot.domain.Option;

public interface GoogleGateway {

	Collection<Option> findOptionsByType(String type, double lat, double lon, double radius);
	
	Collection<Option> findOptionsByText(String text, double lat, double lon, double radius);
	
	Location getLocation(String locationString);
}
