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
	private ActivityCategory activityCategory;
	private int price;
	private int minPersons;
	private int maxPersons;
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
	
	public Activity(String name, String description, ActivityCategory activityCategory, int price, 
			int minPersons, int maxPersons,	Map<ActivityType, Boolean> suitableActivityTypes, 
			Map<WeatherType, Boolean> suitableWeatherTypes) {
		this.name = name;
		this.description = description;
		this.activityCategory = activityCategory;
		this.price = price;
		this.minPersons = minPersons;
		this.maxPersons = maxPersons;
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
