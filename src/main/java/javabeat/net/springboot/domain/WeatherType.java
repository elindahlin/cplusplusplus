package javabeat.net.springboot.domain;

public enum WeatherType {
	CLEAR_SKY(1), NEARLY_CLEAR_SKY(2), VARIABLE_CLOUDINESS(3), HALF_CLEAR_SKY(4),
	CLOUDY_SKY(5), OVERCAST(6), FOG(7), RAIN_SHOWERS(8), THUNDERSTORM(9), 
	LIGHT_SLEET(10), SNOW_SHOWERS(11), RAIN(12), THUNDER(13), SLEET(14), SNOWFALL(15);
	
	private int value;
	
	WeatherType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static WeatherType getWeatherType(int val) {
		WeatherType[] weatherTypes = WeatherType.values();
		for (int i = 0; i < weatherTypes.length; i++) {
			if (weatherTypes[i].value == val) {
				return weatherTypes[i];
			}
		}
		return null;
	}
}
