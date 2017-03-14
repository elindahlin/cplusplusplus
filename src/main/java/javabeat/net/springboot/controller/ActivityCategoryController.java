package javabeat.net.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.ActivityCategory;

@RestController
public class ActivityCategoryController {

	
	@RequestMapping(value = "/activityCategory", method = RequestMethod.GET)
    public @ResponseBody ActivityCategory[] listActivityCategories() {
        return ActivityCategory.values();
    }
}
