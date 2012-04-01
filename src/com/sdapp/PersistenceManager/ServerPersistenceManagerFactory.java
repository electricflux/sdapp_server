package com.sdapp.persistencemanager;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Singleton class
 * @author electricflux
 *
 */
public final class ServerPersistenceManagerFactory {

	private static final PersistenceManagerFactory pmfInstance = 
			JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	private ServerPersistenceManagerFactory()
	{
		
	}
	
	public static PersistenceManagerFactory getInstance()
	{
		return pmfInstance;
	}	
}
