package com.sdapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class LicensePlateMsg {
	
	private static String SEPARATOR = ";";
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent 
	private UserMsg user;
	
	@Persistent(mappedBy = "licensePlateMsg", defaultFetchGroup = "true")
    @Element(dependent = "true")
	private List<PaymentMsg> paymentList;
	
	@Persistent
	private String licensePlateNumber;
	
	public LicensePlateMsg()
	{
		paymentList = new ArrayList<PaymentMsg>();
	}
	
	public UserMsg getUser() {
		return user;
	}

	public void setUser(UserMsg user) {
		this.user = user;
	}

	public List<PaymentMsg> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentMsg> paymentList) {
		this.paymentList = paymentList;
	}

	public static String getSEPARATOR() {
		return SEPARATOR;
	}

	public static void setSEPARATOR(String sEPARATOR) {
		SEPARATOR = sEPARATOR;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}
}
