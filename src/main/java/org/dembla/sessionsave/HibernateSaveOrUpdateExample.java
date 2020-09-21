package org.dembla.sessionsave;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.dembla.sessionsave.TestSessionSave.getTestEmployee;

public class HibernateSaveOrUpdateExample {

    public static void main(String[] args) {

        // Prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

        // save or update example -
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Emp emp = getTestEmployee();
        session.saveOrUpdate(emp);

        emp.setName("Dembla Mayank"); // will be saved into the DB

        System.out.println("9. Before committing saveOrUpdate transaction. Id=" + emp.getId());
        tx.commit();
        System.out.println("10. After committing saveOrUpdate transaction");
        System.out.println("*****");


        Transaction tx2 = session.beginTransaction();
        emp.setName("Updated Test Name"); //Name changed
        emp.getAddress().setCity("Updated City");
        session.saveOrUpdate(emp);
        emp.setName("Kumar"); //again changed to previous value, so no Employee update
        System.out.println("11. Before committing saveOrUpdate transaction. Id=" + emp.getId());
        tx2.commit();
        System.out.println("12. After committing saveOrUpdate transaction");
        System.out.println("*****");

        // Close resources
        sessionFactory.close();


    }

}
