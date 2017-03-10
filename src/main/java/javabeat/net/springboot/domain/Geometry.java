package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

	private String type;
	private double[][] coordinates;
	
	public Geometry() {
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double[][] getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(double[][] coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public String toString() {
		return "Geometry{" +
				"type=" + type +
				"coordinates=" + coordinates +
				"}";
	}
}
