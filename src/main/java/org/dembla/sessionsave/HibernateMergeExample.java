package org.dembla.sessionsave;

import org.dembla.model.Employee;
import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateMergeExample {

    public static void main(String[] args) {

        // Prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Emp emp = (Emp) session.load(Emp.class, new Long(22));
        System.out.println("Employee object loaded. " + emp);
        tx.commit();

        //merge example - data already present in tables
        emp.setSalary(25000);
        Transaction tx8 = session.beginTransaction();

        Emp emp4 = (Emp) session.merge(emp);

        System.out.println(emp4 == emp); // returns false

        emp.setName("Test");
        emp4.setName("Kumar");
        System.out.println("15. Before committing merge transaction");
        tx8.commit();
        System.out.println("16. After committing merge transaction");

        // Close resources
        sessionFactory.close();

    }
}
