package javabeat.net.springboot.domain;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

@Entity
public class Activity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private String url;
	private ActivityCategory activityCategory;
	private int price;
	private int minPersons;
	private int maxPersons;
	private double latitude;
	private double longitude;
	@ElementCollection
    @MapKeyColumn(name="activityType")
    @Column(name="isSuitable")
    @CollectionTable(name="activity_types", joinColumns=@JoinColumn(name="activity_id"))
	private Map<ActivityType, Boolean> suitableActivityTypes;
	@ElementCollection
    @MapKeyColumn(name="weatherType")
    @Column(name="isSuitable")
    @CollectionTable(name="weather_types", joinColumns=@JoinColumn(name="weather_id"))
	private Map<WeatherType, Boolean> suitableWeatherTypes;

	public Activity() {
	}
	
	public Activity(String name, String description, String url, ActivityCategory activityCategory, 
			int price, int minPersons, int maxPersons, double latitude, double longitude, 
			Map<ActivityType, Boolean> suitableActivityTypes, 
			Map<WeatherType, Boolean> suitableWeatherTypes) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.activityCategory = activityCategory;
		this.price = price;
		this.minPersons = minPersons;
		this.maxPersons = maxPersons;
		this.latitude = latitude;
		this.longitude = longitude;
		this.suitableActivityTypes = suitableActivityTypes;
		this.suitableWeatherTypes = suitableWeatherTypes;
	}
	
	public boolean isSuitableFor(ActivityType activityType) {
		return suitableActivityTypes.get(activityType);
	}
	
	public boolean isSuitableFor(WeatherType weatherType) {
		return suitableWeatherTypes.get(weatherType);
	}
	
	public boolean isCloseTo(double lat, double lon, int range) {
		// TODO: calc properly
		return true;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Map<ActivityType, Boolean> getSuitableActivityTypes() {
		return suitableActivityTypes;
	}
	
	public void setSuitableActivityTypes(Map<ActivityType, Boolean> suitableActivityTypes) {
		this.suitableActivityTypes = suitableActivityTypes;
	}
	
	public Map<WeatherType, Boolean> getSuitableWeatherTypes() {
		return suitableWeatherTypes;
	}
	
	public void setSuitableWeatherTypes(Map<WeatherType, Boolean> suitableWeatherTypes) {
		this.suitableWeatherTypes = suitableWeatherTypes;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMinPersons() {
		return minPersons;
	}

	public void setMinPersons(int minPersons) {
		this.minPersons = minPersons;
	}

	public int getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}
	
	@Override
	public String toString() {
		return "Activity{" +
				"name=" + name +
				"}";
	}
}
