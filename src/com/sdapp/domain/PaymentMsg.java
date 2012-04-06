package com.sdapp.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class PaymentMsg {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
    @Persistent
    private Key user;
    
    @Persistent
    private long startTimestamp;
    
    @Persistent
    private long endTimeStamp;
    
    /** This should be a relationship once parking spot objects are stored on server */
    @Persistent
    private long parkingSpotId;
    
    @Persistent
    private float amountPaid;
    
    @Persistent
    private String licensePlate;
}
