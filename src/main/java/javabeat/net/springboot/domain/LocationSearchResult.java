package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationSearchResult {
	 
	private LocationSearchObject[] results;
	private String status;
	
	public LocationSearchResult() {
	}

	public LocationSearchObject[] getResults() {
		return results;
	}

	public void setResults(LocationSearchObject[] results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
