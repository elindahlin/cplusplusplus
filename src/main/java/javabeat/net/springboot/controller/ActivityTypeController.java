package javabeat.net.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.ActivityType;

@RestController
public class ActivityTypeController {

	
	@RequestMapping(value = "/activityType", method = RequestMethod.GET)
    public @ResponseBody ActivityType[] listActivityTypes() {
        return ActivityType.values();
    }
}
