package com.sdapp.domain;

import java.util.ArrayList;
import java.util.List;

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
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	@Persistent
	private String userName;
	
	@Persistent
	private String deviceIdentifier;
	
	@Persistent
	private String authToken;
	
	@Persistent
	private List<String> licensePlates;
	
	public List<String> getLicensePlates() {
		return licensePlates;
	}

	public void setLicensePlates(List<String> licensePlates) {
		this.licensePlates = licensePlates;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Persistent
	private ArrayList<String> licencePlateList;

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

	public ArrayList<String> getLicencePlateList() {
		return licencePlateList;
	}

	public void setLicencePlateList(ArrayList<String> licencePlateList) {
		this.licencePlateList = licencePlateList;
	}
	
	public UserMsg()
	{
		this.licencePlateList = new ArrayList<String>();
	}
}
