package org.dembla.sessionsave;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.dembla.sessionsave.TestSessionSave.getTestEmployee;

public class TestSessionPersist {

    public static void main(String[] args) {

        // Prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory() ;

        // Persist Example
        Session session2 = sessionFactory.openSession() ;
        Transaction tx2 = session2.beginTransaction() ;
        Emp emp2 = getTestEmployee();
        session2.persist(emp2); ;
        System.out.println("Persistent Called ... ");

        emp2.setName("Mayank Dembla"); // will be updated in the db too
        System.out.println("Employee Name Updated !!");
        System.out.println("8. Employee persist called with transaction, id="+emp2.getId()+", address id="+emp2.getAddress().getId());

        tx2.commit();

        /*
        Notice that first employee object is inserted, then at the time of transaction commit,
        update query is executed to update the name value.
        Also mapped object address is saved into database.
         */

        System.out.println("*****");

        session2.close();
        sessionFactory.close();
    }

}
