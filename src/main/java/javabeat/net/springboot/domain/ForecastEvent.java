package javabeat.net.springboot.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastEvent {
	
	private String validTime;
	private Parameter[] parameters;
	
	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public Parameter[] getParameters() {
		return parameters;
	}
	
	public void setParameters(Parameter[] parameters) {
		this.parameters = parameters;
	}
	
	
	@Override
	public String toString() {
		return "ForecastEvent{" + 
					"validTime=" + validTime +
					", parameters=" + Arrays.toString(parameters) +
					"}";
	}

}
