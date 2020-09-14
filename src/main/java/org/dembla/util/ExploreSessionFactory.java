package org.dembla.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

public class ExploreSessionFactory {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory() ;

        // Current Session - no need to close
        Session currenSession = sessionFactory.getCurrentSession() ;


        // Open a new Session ...
        Session newSession = sessionFactory.openSession() ;

        // perform db operation

        // close session
        newSession.close();

        // open a stateless Session

        StatelessSession statelessSession = sessionFactory.openStatelessSession();

        // perform the stateless operation ...

        // close the session .
        statelessSession.close();

        // ** - close session factory
        sessionFactory.close();
    }

}
