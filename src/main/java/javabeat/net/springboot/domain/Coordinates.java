package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
	
	public Coordinates() {
	}
	
	@Override
	public String toString() {
		return "Coordinates{" +
				"empty" +
				"}";
	}

}
