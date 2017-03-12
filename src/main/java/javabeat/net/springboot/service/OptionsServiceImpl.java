package javabeat.net.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

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
			options = googleGateway.findOptionsByType("cafe", lat, lon, radius);
			break;
		case CINEMA:
			options = googleGateway.findOptionsByType("movie_theater", lat, lon, radius);
			break;
		case MUSEUM:
			options = googleGateway.findOptionsByType("museum", lat, lon, radius);
			break;
		case THEATER:
			options = googleGateway.findOptionsByText("theatre", lat, lon, radius);
			break;
		case PAINTBALL:
			options = googleGateway.findOptionsByText("paintball", lat, lon, radius);
			options.stream()
				.filter(option -> !Arrays.asList(option.getTypes()).contains("store"))
				.collect(Collectors.toList());
			break;
		case LASERTAG:
			options = googleGateway.findOptionsByText("laser+tag", lat, lon, radius);
			break;
		case PARK:
			options = googleGateway.findOptionsByText("park", lat, lon, radius);
			break;
		case RESTAURANT:
			options = googleGateway.findOptionsByType("restaurant", lat, lon, radius);
			break;
		case ESCAPEROOM:
			options = googleGateway.findOptionsByText("escape+room", lat, lon, radius);
			break;
		case SPA:
			options = googleGateway.findOptionsByType("spa", lat, lon, radius);
			break;
		case ICESKATING:
			options = googleGateway.findOptionsByText("ice+skating", lat, lon, radius);
			options.stream()
				.filter(option -> !Arrays.asList(option.getTypes()).contains("store"))
				.collect(Collectors.toList());
			break;
		case BOWLING:
			options = googleGateway.findOptionsByType("bowling_alley", lat, lon, radius);
			break;
		case CLIMBING:
			options = googleGateway.findOptionsByText("climbing", lat, lon, radius);
			break;
		case BILLIARDS:
			options = googleGateway.findOptionsByText("billiard", lat, lon, radius);
			options.stream()
				.filter(option -> !Arrays.asList(option.getTypes()).contains("store"))
				.collect(Collectors.toList());
			break;
		case SWIMMING:
			options = googleGateway.findOptionsByText("swimming", lat, lon, radius);
			options.stream()
				.filter(option -> !Arrays.asList(option.getTypes()).contains("store"))
				.collect(Collectors.toList());
			break;
		case SHOPPING:
			options = googleGateway.findOptionsByType("shopping_mall", lat, lon, radius);
			options.addAll(googleGateway.findOptionsByType("clothing_store", lat, lon, radius));
			options.addAll(googleGateway.findOptionsByType("shoe_store", lat, lon, radius));
			break;
		default:
			LOGGER.warn("category option search method undefined for type: " + category);
			options = Collections.emptyList();
			break;
		}
		return options;
	}
}
