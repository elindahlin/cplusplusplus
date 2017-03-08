package javabeat.net.springboot.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javabeat.net.springboot.domain.Forecast;
import javabeat.net.springboot.service.DataService;

@RestController
public class DataController {
	
	private final DataService dataService;
	
	@Inject
	public DataController(final DataService dataService) {
		this.dataService = dataService;
	}
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
    public @ResponseBody Forecast requestData() {
        Forecast forecast = dataService.getData();
        return forecast;
    }

}
