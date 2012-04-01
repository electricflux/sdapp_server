package com.sdapp.persistencemanager;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.sdapp.domain.UserMsg;

public class DAO {

	/**
	 * Get user object
	 * @param user
	 * @return
	 */
	public static UserMsg getUser(UserMsg user)
	{
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();
		Key key = KeyFactory.createKey(UserMsg.class.getSimpleName(), user.getUserName());
		UserMsg verifiedUser = null;
		try {
			verifiedUser = pm.getObjectById(UserMsg.class,key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}

		return verifiedUser;
	}

	/**
	 * Save user object
	 * @param user
	 */
	public static void saveUser(UserMsg user)
	{
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(UserMsg.class.getSimpleName(), user.getUserName());
	        user.setKey(key);
			pm.makePersistent(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}

	/**
	 * Update user object
	 * @param user
	 * @param auth
	 */
	
	public static void updateUser(UserMsg user, String auth) {
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();
		try {
			UserMsg msg = pm.getObjectById(UserMsg.class, user.getKey());
			msg.setAuthToken(auth);   
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}
}
