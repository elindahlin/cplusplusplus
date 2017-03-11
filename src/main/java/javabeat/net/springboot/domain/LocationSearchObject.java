package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationSearchObject {

	private GoogleGeometry geometry;
	
	public LocationSearchObject() {
	}

	public GoogleGeometry getGeometry() {
		return geometry;
	}
	
	public void setGeometry(GoogleGeometry geometry) {
		this.geometry = geometry;
	}
}
