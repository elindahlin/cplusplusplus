package javabeat.net.springboot.service;

import javabeat.net.springboot.domain.Location;

public interface LocationService {

	Location getLocation(String locationString);
	
	String getLocation(double lat, double lon);
}
