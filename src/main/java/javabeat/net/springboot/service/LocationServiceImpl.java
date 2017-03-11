package javabeat.net.springboot.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javabeat.net.springboot.domain.Location;
import javabeat.net.springboot.gateway.GoogleGateway;

@Service
@Validated
public class LocationServiceImpl implements LocationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OptionsServiceImpl.class);
	private final GoogleGateway googleGateway;

	@Inject
	public LocationServiceImpl(final GoogleGateway googleGateway) {
		this.googleGateway = googleGateway;
	}
	
	@Override
	public Location getLocation(String locationString) {	
		return googleGateway.getLocation(locationString);
	}

}
