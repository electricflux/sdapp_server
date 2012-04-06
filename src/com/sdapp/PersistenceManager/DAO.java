package com.sdapp.persistencemanager;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.sdapp.domain.LicensePlateMsg;
import com.sdapp.domain.UserMsg;
import com.sdapp.logger.SdLogger;

public class DAO {

	/**
	 * Get user object
	 * @param user
	 * @return
	 */
	public static UserMsg getUser(String username, boolean loadLicensePlateMessageList)
	{
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();
		/** Create the query */
		Query query = pm.newQuery(UserMsg.class);
		query.setFilter("userName == userNameParam");
		query.declareParameters("String userNameParam");

		UserMsg verifiedUser = null;
		try {
			List<UserMsg> results = (List<UserMsg>) query.execute(username);
			SdLogger.getInstance().getLogger().info("Result set from getUser query: "+results.size());
			if (results.size() == 1)
			{
				verifiedUser = results.get(0);
				/** Touch all license plate objects */
				if (loadLicensePlateMessageList)
				{
					for (LicensePlateMsg msg : verifiedUser.getLicensePlateList())
					{
						msg.getKey();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			SdLogger.getInstance().getLogger().info(e.getMessage());
		} finally {
			query.closeAll();
			pm.close();
		}
		return verifiedUser;
	}

	/**
	 * Get license message object. To prevent cyclic dependencies, 
	 * don't load the user object when you load the license plate msg
	 * object.
	 * @param key
	 * @return
	 */
	public static LicensePlateMsg getLicensePlateMsg(Key key)
	{
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();

		LicensePlateMsg msg = null;
		try {
			msg  = pm.getObjectById(LicensePlateMsg.class, key);
		} catch (Exception e) {
			e.printStackTrace();
			SdLogger.getInstance().getLogger().info(e.getMessage());
		} finally {
			pm.close();
		}
		return msg;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static UserMsg getUser(Key key)
	{
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();

		UserMsg msg = null;
		try {
			msg  = pm.getObjectById(UserMsg.class, key);
		} catch (Exception e) {
			e.printStackTrace();
			SdLogger.getInstance().getLogger().info(e.getMessage());
		} finally {
			pm.close();
		}
		return msg;
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
			pm.makePersistent(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}

	/**
	 * 
	 * @param msg
	 */
	public static void saveLicensePlateMsg(LicensePlateMsg msg) {
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();
		try {
			pm.makePersistent(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pm.close();
		}
	}

	public static LicensePlateMsg getLicensePlateMsg(
			String licensePlateNumber, boolean loadUserMsg)
	{
		PersistenceManager pm = 
				ServerPersistenceManagerFactory.getInstance().getPersistenceManager();
		/** Create the query */
		Query query = pm.newQuery(LicensePlateMsg.class);
		query.setFilter("licensePlateNumber == licensePlateNumberParam");
		query.declareParameters("String licensePlateNumberParam");

		LicensePlateMsg licensePlateMsg = null;
		try {
			List<LicensePlateMsg> results = (List<LicensePlateMsg>) query.execute(licensePlateNumber);
			SdLogger.getInstance().getLogger().info("Result set from getLicensePlateMsg query: "+results.size());
			if (results.size() == 1)
			{
				licensePlateMsg = results.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SdLogger.getInstance().getLogger().info(e.getMessage());
		} finally {
			query.closeAll();
			pm.close();
		}
		return licensePlateMsg;
	}
}
