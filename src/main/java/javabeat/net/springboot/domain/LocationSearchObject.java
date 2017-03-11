package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationSearchObject {

	private GoogleGeometry geometry;
	private AddressComponent[] address_components;
	
	public LocationSearchObject() {
	}

	public GoogleGeometry getGeometry() {
		return geometry;
	}
	
	public void setGeometry(GoogleGeometry geometry) {
		this.geometry = geometry;
	}

	public AddressComponent[] getAddress_components() {
		return address_components;
	}

	public void setAddress_components(AddressComponent[] address_components) {
		this.address_components = address_components;
	}
}
