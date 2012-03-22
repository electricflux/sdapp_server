package com.sdapp.domain;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserMsg {

	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key key;
	
	@Persistent
	private String userName;
	
	@Persistent 
	private String md5Password;
	
	@Persistent
	private String deviceIdentifier;
	
	@Persistent
	private ArrayList<String> licencePlateList;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMd5Password() {
		return md5Password;
	}

	public void setMd5Password(String md5Password) {
		this.md5Password = md5Password;
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
