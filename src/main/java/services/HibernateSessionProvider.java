package services;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateSessionProvider 
{
	public static  Session getSession()
	{
		return new Configuration().addResource("ivrrm.hbm.xml").buildSessionFactory().openSession();
	}

}
