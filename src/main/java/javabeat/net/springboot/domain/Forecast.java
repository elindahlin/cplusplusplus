package javabeat.net.springboot.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
	
	private String approvedTime;
	private String referenceTime;
	private Geometry geometry;
	private ForecastEvent[] timeSeries;
	
	public Forecast() {
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public ForecastEvent[] getTimeSeries() {
		return timeSeries;
	}
	
	public void setTimeSeries(ForecastEvent[] timeSeries) {
		this.timeSeries = timeSeries;
	}
	
	public String getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(String approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getReferenceTime() {
		return referenceTime;
	}

	public void setReferenceTime(String referenceTime) {
		this.referenceTime = referenceTime;
	}
	
	
	@Override
	public String toString() {
		return "Forecast{" + 
					"approvedTime=" + approvedTime +
					", referenceTime=" + referenceTime +
					", geometry=" + geometry +
					", timeSeries=" + Arrays.toString(timeSeries) +
					"}";
	}
}
