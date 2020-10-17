package org.dembla.criteria;

import org.dembla.sessionsave.Emp;
import org.dembla.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateCriteria {

    public static void main(String[] args) {

        // Prep Work
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory() ; 
        Session session = sessionFactory.getCurrentSession() ;
        Transaction tx = session.beginTransaction() ;


        // Get all Employees
        CriteriaBuilder builder = session.getCriteriaBuilder() ;
        CriteriaQuery<Emp> cr = builder.createQuery(Emp.class) ;
        Root<Emp> root = cr.from(Emp.class) ;
        cr.select(root) ;

        Query<Emp> query = session.createQuery(cr) ;
        List<Emp> results = query.getResultList() ;

        results.forEach(i-> System.out.println(i));

        // Get all Employees Salary greater than 1000
        System.out.println("------------");
        cr.select(root).where(builder.gt(root.get("salary"),1000)) ;
        query = session.createQuery(cr) ;
        results = query.getResultList() ;

        results.forEach(i-> System.out.println(i));

        // Get all Employees Salary lesser than 1000
        System.out.println("------------");
        cr.select(root).where(builder.lt(root.get("salary"),1000)) ;
        query = session.createQuery(cr) ;
        results = query.getResultList() ;

        results.forEach(i-> System.out.println(i));

        // Employee having the Name contains test
        System.out.println("------------");
        cr.select(root).where(builder.like(root.get("name"),"%Test%"))
                        .where(builder.like(root.get("name"),"%test%")) ;

        query = session.createQuery(cr) ;
        results = query.getResultList() ;

        results.forEach(i-> System.out.println(i));

        // Employee Id in between 5 and 12
        System.out.println("------------");
        cr.select(root).where(builder.between(root.get("id"),5,12)) ;

        query = session.createQuery(cr) ;
        results = query.getResultList() ;

        results.forEach(i-> System.out.println(i));

    }

}
