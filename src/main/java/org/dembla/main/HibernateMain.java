package org.dembla.main;

import org.dembla.model.Employee;
import org.dembla.model.Employee1;
import org.dembla.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

public class HibernateMain {

    public static void main(String[] args) {
        Employee1 employee = new Employee1() ;
        employee.setName("Mayank");
        employee.setRole("Software Developer");
        employee.setInsertTime(new Date());

        // Get Session
//or    Session session = HibernateUtil.getSessionFactory().getCurrentSession() ;
//or    Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession() ;
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession() ;

        // start transaction
        session.beginTransaction() ;

        // save the model class
        session.save(employee) ;

        // commit Transaction
        session.getTransaction().commit();
        System.out.println("Employee ID " + employee.getId());

        // terminate the session , other wise the program would not end
        HibernateUtil.getSessionFactory().close();

    }
}
