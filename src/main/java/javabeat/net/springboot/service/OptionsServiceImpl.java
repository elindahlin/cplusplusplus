package javabeat.net.springboot.service;

import java.util.Collection;
import java.util.Collections;

import javabeat.net.springboot.domain.ActivityCategory;
import javabeat.net.springboot.domain.Option;

public class OptionsServiceImpl implements OptionsService {

	@Override
	public Collection<Option> getOptions(ActivityCategory category, double lat, double lon) {
		
		return Collections.emptyList();
	}

}
