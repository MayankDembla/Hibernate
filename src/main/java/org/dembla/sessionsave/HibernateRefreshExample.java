package org.dembla.sessionsave;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.dembla.sessionsave.TestSessionSave.getTestEmployee;

public class HibernateRefreshExample {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Emp emp = getTestEmployee();
        emp.setName("Lokesh");

        session.save(emp);
        tx.commit();
        session.close();

        // verify the employees name
        System.out.println(verifyEmployeeFirstName(new Long(25), "Lokesh"));

        Session sessionTwo = HibernateUtil.getSessionAnnotationFactory().openSession();
        sessionTwo.beginTransaction();

        emp.setName("Dembla");
        sessionTwo.refresh(emp); // refresh from db.

        System.out.println(" Name is : " + emp.getName());
        sessionTwo.getTransaction().commit();
        sessionTwo.close();

        System.out.println(emp.getName().equals("Lokesh"));

    }

    private static boolean verifyEmployeeFirstName(Long employeeId, String firstName){
        Session session = HibernateUtil.getSessionAnnotationFactory().openSession();
        Emp employee = (Emp) session.load(Emp.class, employeeId);

        //Verify first name
        boolean result = firstName.equals(employee.getName());
        session.close();

        //Return verification result
        return result;
    }

}
