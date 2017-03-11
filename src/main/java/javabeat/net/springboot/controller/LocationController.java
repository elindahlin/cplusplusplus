package javabeat.net.springboot.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.Location;
import javabeat.net.springboot.service.LocationService;

@RestController
public class LocationController {
	
	private LocationService locationService;
	
	@Inject
	public LocationController(final LocationService locationService) {
		this.locationService = locationService;
	}

	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public Location addActivity(@RequestParam(value = "place", required = true) String place) {
		return locationService.getLocation(place);
	}
}
