package com.fabian.airport.impl;

import com.fabian.airport.util.AirportHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class PersistenceServiceImpl {
    private static final SessionFactory HIBERNATE_SESSION = AirportHibernateUtil.getSessionFactory();

    public static <T> void save(T entity) {
        Session session = HIBERNATE_SESSION.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public static <T> List<T> query(Class<T> clazz) {
        Session session = HIBERNATE_SESSION.openSession();
        Transaction transaction = session.beginTransaction();
        String query = String.format("from %s", clazz.getSimpleName());
        List<T> list = session.createQuery(query).list();
        transaction.commit();
        session.close();
        return list;
    }

    public static <T> T find(Class<T> clazz, Serializable id) {
        Session session = HIBERNATE_SESSION.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(String.format("from %s where id = %s", clazz.getSimpleName(), id));
        T result = (T) query.uniqueResult();

        transaction.commit();
        session.close();
        return result;
    }

    public static <T> List<T> query(String queryStr) {
        Session session = HIBERNATE_SESSION.openSession();
        Transaction transaction = session.beginTransaction();

        List<T> result = session.createQuery(queryStr).list();


        transaction.commit();
        session.close();
        return result;
    }
}
