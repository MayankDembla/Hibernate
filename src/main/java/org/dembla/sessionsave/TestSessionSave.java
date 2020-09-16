package org.dembla.sessionsave;

import org.dembla.model.Employee;
import org.dembla.util.HibernateUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TestSessionSave {

    public static void main(String[] args) {

        // prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();

        // save Example With and without flush
        Emp emp = getTestEmployee();
        long id = (Long) session.save(emp) ;
        System.out.println("1. Employee save called without transaction, id="+id);

        // on Commenting below line will only store the primary entity i.e. Employee here Not Address.
        session.flush(); //address will not get saved without this
        System.out.println("*****");

        session.close();
        sessionFactory.close();

    }

    private static Emp getTestEmployee() {

        Emp emp = new Emp() ;
        Address address = new Address() ;
        emp.setName("Test emp");
        emp.setSalary(1000);

        address.setCity("Test City");
        address.setZipCode("121112");
        emp.setAddress(address);
        address.setEmployee(emp);

        return emp ;
    }

}
