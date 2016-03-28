/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabian.airport.util;

import com.google.common.collect.Maps;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author fabian
 */
public class AirportHibernateUtil {
    private static final Logger _log = LoggerFactory.getLogger(AirportHibernateUtil.class);

    private static final SessionFactory sessionFactory;
    
    static {
        System.out.println("START HIBERNATE");
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Map<String, ClassMetadata> allClassMetadata = sessionFactory.getAllClassMetadata();
            for (Map.Entry<String, ClassMetadata> stringClassMetadataEntry : allClassMetadata.entrySet()) {
                System.out.println("metadata = " + stringClassMetadataEntry);
            }

//            sessionFactory = new Configuration().configure().buildSessionFactory(registry);
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            System.out.println("CONFIGURED HIBERNATE!");
        } catch (Throwable ex) {
            System.err.println("EXCEPTION CONFIGURING HIBERNATE");
            ex.printStackTrace();
            _log.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
        System.out.println("FINISH HIBERNATE CONFIG");
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
