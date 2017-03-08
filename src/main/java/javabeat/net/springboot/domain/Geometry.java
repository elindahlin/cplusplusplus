package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

	private String type;
//	private Coordinates coordinates;
	
	public Geometry() {
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
//	public Coordinates getCoordinates() {
//		return coordinates;
//	}
//	
//	public void setCoordinates(Coordinates coordinates) {
//		this.coordinates = coordinates;
//	}
	
	@Override
	public String toString() {
		return "Geometry{" +
				"type=" + type +
//				"coordinates=" + coordinates +
				"]";
	}
}
