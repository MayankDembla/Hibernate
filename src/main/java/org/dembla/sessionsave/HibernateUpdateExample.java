package org.dembla.sessionsave;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.dembla.sessionsave.TestSessionSave.getTestEmployee;

public class HibernateUpdateExample {

    public static void main(String[] args) {

        // prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Emp emp = session.load(Emp.class, new Long(22));
        System.out.println("Employee object loaded. " + emp);
        tx.commit();

        // update Example
        emp.setName("Updated name");
        emp.getAddress().setCity("Bangalore");

        Transaction tx7 = session.beginTransaction();
        session.update(emp);

        emp.setName("Final updated name");
        System.out.println("13. Before committing update transaction");
        tx7.commit();

        System.out.println("14. After committing update transaction");

        // Close resources
        session.close();
        sessionFactory.close();
    }
}
