package javabeat.net.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameter {
	
	private String name;
	private String levelType;
	private int level;
	private String unit;
	private Number[] values;
	
	public Parameter() {
	}
	
	public Parameter(String name, Number value) {
		this.name = name;
		this.values = new Number[]{ value };
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Number[] getValues() {
		return values;
	}

	public void setValues(Number[] values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		return "Parameter{" + 
				"name=" + name +
				", levelType=" + levelType +
				", level=" + level + 
				", unit=" + unit +
				", value=" + values[0] +
				"}";
	}
}
