package org.dembla.hql;

import org.dembla.model.Employee;
import org.dembla.sessionsave.Emp;
import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    }

}
