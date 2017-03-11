package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceSearchResult {

	private String nextPlaceToken;
	private String status;
	private PlaceSearchObject[] results;
	
	public PlaceSearchResult() {
	}

	public String getNextPlaceToken() {
		return nextPlaceToken;
	}

	public void setNextPlaceToken(String nextPlaceToken) {
		this.nextPlaceToken = nextPlaceToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PlaceSearchObject[] getResults() {
		return results;
	}

	public void setResults(PlaceSearchObject[] results) {
		this.results = results;
	}
}
