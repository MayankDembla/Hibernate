package org.dembla.hql;

import org.dembla.sessionsave.Emp;
import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HQLExamples {

    public static void main(String[] args) {

        // Prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

        // .... CRUD Operations ....

        // 1.  HQL example - get all employees
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Emp");
        List<Emp> listEmp = query.list();

        listEmp.stream().forEach(s -> System.out.println(s));

        //2. HQL example - Get Employee with id
        query = session.createQuery("from Emp where id = :id");
        query.setParameter("id", (long) 3); // No Address Stored Coorosponding to it
        Emp emp = (Emp) query.uniqueResult();

        System.out.println("Employee Name : " + emp.getName() + " City : " + emp.getAddress().getCity());

        // Pagination Example
        System.out.println("====================================>>>>>");
        query = session.createQuery("from Emp");
        query.setFirstResult(2); // starts with 2
        query.setFetchSize(5);

        List<Emp> emplist = query.list();

        for (Emp emp1 : emplist) {
            System.out.println("Paginated Employees : : " + emp1.getId() + " Name : " + emp1.getName() +  " , " + emp1.getAddress().getCity());
        }

        // HQL Update Query
        query  = session.createQuery("update Emp set name = :name where id = :id ") ;
        query.setParameter("id", (long)1) ;
        query.setParameter("name", "Test emp New" )  ;
        int result = query.executeUpdate();
        System.out.println("Employee Update Status="+result);

        //HQL Delete Employee, we need to take care of foreign key constraints too
        query  = session.createQuery("delete from Emp where id = :id ") ;
        query.setParameter("id", (long)1) ;
        result = query.executeUpdate() ;
        System.out.println("Employee Delete Status : " + result)  ;

      // HQL Aggregrate Function Examples
        query = session.createQuery("select sum(salary) from Emp") ;
        double sumSalary  = (Double)  query.uniqueResult()  ;
        System.out.println(" Sum of All Salaries  : " + sumSalary );


        tx.commit();
    }

}
