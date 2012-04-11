package com.sdapp.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class PaymentMsg {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@JsonIgnore
    @Persistent
    private LicensePlateMsg licensePlateMsg;
    
    @Persistent
    private long startTimestamp;
    
    @Persistent
    private long endTimeStamp;
    
    /** This should be a relationship once parking spot objects are stored on server */
    @Persistent
    private long parkingSpotId;
    
    @Persistent
    private float amountPaid;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public LicensePlateMsg getLicensePlateMsg() {
		return licensePlateMsg;
	}

	public void setLicensePlateMsg(LicensePlateMsg licensePlateMsg) {
		this.licensePlateMsg = licensePlateMsg;
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public long getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(long endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

	public long getParkingSpotId() {
		return parkingSpotId;
	}

	public void setParkingSpotId(long parkingSpotId) {
		this.parkingSpotId = parkingSpotId;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}
}
