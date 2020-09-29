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

    }

}
