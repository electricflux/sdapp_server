package com.sdapp.PersistenceManager;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.sdapp.domain.UserMsg;

public class DAO {

	public static UserMsg getUser(UserMsg user)
	{
		PersistenceManager pm = ServerPersistenceManagerFactory.getInstance().getPersistenceManager();
		UserMsg verifiedUser = null;
		try {
			verifiedUser = pm.getObjectById(UserMsg.class,user.getUserName());
		} catch (JDOObjectNotFoundException e) {
		}
		
		if (verifiedUser == null)
			return null;
		
		if (verifiedUser.getMd5Password().equals(user.getMd5Password()))
			return verifiedUser;
		else
			return null;
	}
}
