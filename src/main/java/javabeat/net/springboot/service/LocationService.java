package javabeat.net.springboot.service;

import javabeat.net.springboot.domain.City;
import javabeat.net.springboot.domain.Location;

public interface LocationService {

	Location getLocation(String locationString);
	
	City getLocation(double lat, double lon);
}
