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

	
}
