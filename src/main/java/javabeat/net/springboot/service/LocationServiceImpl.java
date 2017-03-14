package javabeat.net.springboot.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javabeat.net.springboot.domain.City;
import javabeat.net.springboot.domain.Location;
import javabeat.net.springboot.gateway.GoogleGateway;

@Service
@Validated
public class LocationServiceImpl implements LocationService {

	private final GoogleGateway googleGateway;

	@Inject
	public LocationServiceImpl(final GoogleGateway googleGateway) {
		this.googleGateway = googleGateway;
	}
	
	@Override
	public Location getLocation(String locationString) {	
		return googleGateway.getLocation(locationString);
	}

	@Override
	public City getLocation(double lat, double lon) {
		return googleGateway.getLocationName(lat, lon);
	}
}
