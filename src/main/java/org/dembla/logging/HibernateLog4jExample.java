package org.dembla.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.dembla.sessionsave.Emp;
import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateLog4jExample {

    static{
        System.out.println("Before log4j configuration");
        DOMConfigurator.configure("log4j.xml");
        System.out.println("After log4j configuration");
    }

    private static Logger logger = Logger.getLogger(HibernateLog4jExample.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        //Prep work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

        //Get All Employees
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Emp");
        List<Emp> empList = query.list();
        for(Emp emp : empList){
            logger.info("List of Employees::"+emp.getId()+","+emp.getAddress().getCity());
        }

        tx.commit();
        sessionFactory.close();
        logger.info("DONE");
    }

}
