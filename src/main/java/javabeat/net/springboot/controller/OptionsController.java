package javabeat.net.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.ActivityCategory;

@RestController
public class OptionsController {

	
	@RequestMapping(value = "/option", method = RequestMethod.GET)
    public @ResponseBody String listCategorySuggestions(
    		@RequestParam(value = "latitude", required = true) double latitude,
			@RequestParam(value = "longitude", required = true) double longitude,
			@RequestParam(value = "activityCategory", required = true) String activityCategory) {
		ActivityCategory category = ActivityCategory.valueOf(activityCategory.toUpperCase());
        return "finding category";
    }
}
