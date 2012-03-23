package com.sdapp.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ParkingSpotMsg {
	
	@PrimaryKey
	private Long id;
	
	@Persistent
	private float latitude;
	
	@Persistent
	private float longitude;
	
	@Persistent 
	private int rate;
	
	@Persistent
	private int duration;
	
	@Persistent
	private int type;
	
	@Persistent
	private int quantity;
	
	@Persistent
	private String address;

	@Persistent
	private String attendent;

	@Persistent
	private String contact;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public int getNumSpots() {
		return numSpots;
	}

	public void setNumSpots(int numSpots) {
		this.numSpots = numSpots;
	}

	public String getParkingSpotName() {
		return parkingSpotName;
	}

	public void setParkingSpotName(String parkingSpotName) {
		this.parkingSpotName = parkingSpotName;
	}

	public float getCostPerMinute() {
		return costPerMinute;
	}

	public void setCostPerMinute(float costPerMinute) {
		this.costPerMinute = costPerMinute;
	}

	public float getMaxNumMinutes() {
		return maxNumMinutes;
	}

	public void setMaxNumMinutes(float maxNumMinutes) {
		this.maxNumMinutes = maxNumMinutes;
	}

	public long getMaxMinutes() {
		return maxMinutes;
	}

	public void setMaxMinutes(long maxMinutes) {
		this.maxMinutes = maxMinutes;
	}
}
