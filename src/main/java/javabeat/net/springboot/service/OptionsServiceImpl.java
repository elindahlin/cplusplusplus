package javabeat.net.springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javabeat.net.springboot.domain.ActivityCategory;
import javabeat.net.springboot.domain.Option;
import javabeat.net.springboot.gateway.GoogleGateway;

@Service
@Validated
public class OptionsServiceImpl implements OptionsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OptionsServiceImpl.class);
	private final GoogleGateway googleGateway;

	@Inject
	public OptionsServiceImpl(final GoogleGateway googleGateway) {
		this.googleGateway = googleGateway;
	}

	@Override
	public Collection<Option> getOptions(ActivityCategory category, double lat, double lon, double radius) {
		Collection<Option> options = new ArrayList<>();
		switch (category) {
		case COFFEE:
			String type = "cafe";
			options = googleGateway.findOptionsByType(type, lat, lon, radius);
			break;
		case ESCAPEROOM:
			String text = "escape+room";
			options = googleGateway.findOptionsByText(text, lat, lon, radius);
			break;
		default:
			LOGGER.warn("category option search method undefined");
			options = Collections.emptyList();
			break;
		}
		return options;
	}
}
