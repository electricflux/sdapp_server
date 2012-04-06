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
public class UserMsg {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String userName;
	
	@Persistent
	private String deviceIdentifier;
	
	@Persistent
	private String authToken;
	
	@Persistent(mappedBy = "user", defaultFetchGroup = "true")
    @Element(dependent = "true")
	private List<LicensePlateMsg> licensePlateList;
	
	/** Constructor */
	public UserMsg()
	{
		licensePlateList = new ArrayList<LicensePlateMsg>();
	}
	
	public List<LicensePlateMsg> getLicensePlateList() {
		return licensePlateList;
	}

	public void setLicensePlateList(List<LicensePlateMsg> licensePlateList) {
		this.licensePlateList = licensePlateList;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceIdentifier() {
		return deviceIdentifier;
	}

	public void setDeviceIdentifier(String deviceIdentifier) {
		this.deviceIdentifier = deviceIdentifier;
	}
}
