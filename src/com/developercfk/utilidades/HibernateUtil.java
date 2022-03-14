package com.developercfk.utilidades;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * 
 * 
 *
 * @author developerCFK
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    static {
        try {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            setSessionFactory(new Configuration().configure().buildSessionFactory());
        } catch (SecurityException | HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public HibernateUtil() {
    }

    /**
     * @param aSessionFactory the sessionFactory to set
     */
    public static void setSessionFactory(SessionFactory aSessionFactory) {
        sessionFactory = aSessionFactory;
    }
    
    
}
