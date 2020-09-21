package org.dembla.sessionsave;

import org.dembla.model.Employee;
import org.dembla.util.HibernateUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Random;

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


        // Session Example With Transaction
        Session session1 =  sessionFactory.openSession() ;
        Emp emp1 = getTestEmployee()  ;
        long id1 = (Long) session1.save(emp1) ;
        System.out.println("2. Employee save called with transaction, id="+id1);
        System.out.println("3. Before committing save transaction");
        tx1.commit();
        System.out.println("4. After committing save transaction");
        System.out.println("*****");

        //save example - existing row in table
        Session session6 = sessionFactory.openSession();
        Transaction tx6 = session6.beginTransaction();
        Emp emp6 =  (Emp) session6.load(Emp.class, new Long(11));

        //update some data
        System.out.println("Employee Details="+emp6);
        emp6.setName("New Name");
        emp6.getAddress().setCity("New City");

        long id6 = (Long) session6.save(emp6);
        emp6.setName("New Name1"); // will get updated in database
        System.out.println("5. Employee save called with transaction, id="+id6);
        System.out.println("6. Before committing save transaction");
        tx6.commit();
        System.out.println("7. After committing save transaction");
        System.out.println("*****");

        // Close resources
        session.close();
        session1.close();
        session6.close();
        sessionFactory.close();

    }

    public static Emp getTestEmployee() {

        Emp emp = new Emp() ;
        Address address = new Address() ;
        emp.setName("Test emp");
        emp.setSalary(new Random().nextInt());

        address.setCity("Test City");
        address.setZipCode("121112");
        emp.setAddress(address);
        address.setEmployee(emp);

        return emp ;
    }

}
