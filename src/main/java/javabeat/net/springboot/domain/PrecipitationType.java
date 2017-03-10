package javabeat.net.springboot.domain;

public enum PrecipitationType {
	NO_PRECIPITATION(0), SNOW(1), SNOW_AND_RAIN(2), RAIN(3), DRIZZLE(4), FREEZING_RAIN(5), 
	FREEZING_DRIZZLE(6);
	
	private int value;
	
	PrecipitationType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static PrecipitationType getPrecipitationType(int val) {
		PrecipitationType[] precipitationTypes = PrecipitationType.values();
		for (int i = 0; i < precipitationTypes.length; i++) {
			if (precipitationTypes[i].value == val) {
				return precipitationTypes[i];
			}
		}
		return null;
	}
}
