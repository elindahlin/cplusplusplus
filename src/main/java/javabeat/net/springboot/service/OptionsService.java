package javabeat.net.springboot.service;

import java.util.Collection;

import javabeat.net.springboot.domain.ActivityCategory;
import javabeat.net.springboot.domain.Option;

public interface OptionsService {

	Collection<Option> getOptions(ActivityCategory category, double lat, double lon);
}
