package org.dembla.util;

import org.dembla.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Hibernategetvsload {

    public static void main(String[] args) {

        // Prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory() ;
        Session session = sessionFactory.openSession() ;

        Transaction tx = session.beginTransaction() ;

        // Get Example -- Early Fire Query
        Employee employee = session.get(Employee.class, new Integer(2)) ;

        System.out.println("Employee get called");
        System.out.println("Employee ID= "+employee.getId());
        System.out.println("Employee Get Details:: "+employee+"\n");

        // Load Example --- Lazyily Fire the Query
        Employee employee1 = session.load(Employee.class,new Integer(1)) ;

        System.out.println("Employee load called");
        System.out.println("Employee ID= "+employee1.getId());
        System.out.println("Employee load Details:: "+employee1+"\n");

        /*
         From the output it’s clear that get() returns the object by fetching it from database or from hibernate cache
         whereas
         load() just returns the reference of an object that might not actually exists,
         it loads the data from database or cache only when you access other properties of the object.
         */


        // close resource
        tx.commit();
       sessionFactory.close();


    }

}
