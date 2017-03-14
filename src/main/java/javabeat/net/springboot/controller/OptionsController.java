package javabeat.net.springboot.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.ActivityCategory;
import javabeat.net.springboot.domain.Option;
import javabeat.net.springboot.service.OptionsService;

@RestController
public class OptionsController {

	private OptionsService optionsService;
	
	@Inject
	public OptionsController(final OptionsService optionsService) {
		this.optionsService = optionsService;
	}
	
	@RequestMapping(value = "/option", method = RequestMethod.GET)
    public @ResponseBody Collection<Option> listCategorySuggestions(
    		@RequestParam(value = "latitude", required = true) double latitude,
			@RequestParam(value = "longitude", required = true) double longitude,
			@RequestParam(value = "activityCategory", required = true) String activityCategory,
			@RequestParam(value = "radius", required = true) double radius) {
		ActivityCategory category = ActivityCategory.valueOf(activityCategory.toUpperCase());
		Collection<Option> options = optionsService.getOptions(category, latitude, longitude, radius);
        return options;
    }
}
