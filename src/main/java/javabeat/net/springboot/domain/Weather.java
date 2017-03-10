package javabeat.net.springboot.domain;

public class Weather {
	
	private String dateTime;
	private double temperature;
	private WeatherType weatherType;
	private PrecipitationType precipitationType;

	public Weather() {
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public WeatherType getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(WeatherType weatherType) {
		this.weatherType = weatherType;
	}
	
	public PrecipitationType getPrecipitationType() {
		return precipitationType;
	}

	public void setPrecipitationType(PrecipitationType precipitationType) {
		this.precipitationType = precipitationType;
	}

	@Override
	public String toString() {
		return "Weather{" + 
				"dateTime=" + dateTime + 
				", temperature=" + temperature + 
				", weatherType=" + weatherType +
				", precipitationType=" + precipitationType + 
				"}";
	}
}
