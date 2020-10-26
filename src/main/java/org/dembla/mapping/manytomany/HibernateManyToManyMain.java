package org.dembla.mapping.manytomany;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class HibernateManyToManyMain {

    public static void main(String[] args) {
        Item1 item1 = new Item1();
        item1.setDescription("samsung");
        item1.setPrice(300);

        Item1 item2 = new Item1();
        item2.setDescription("nokia");
        item2.setPrice(200);

        CartNew cart = new CartNew();
        cart.setTotal(500);

        Set<Item1> items = new HashSet<Item1>();
        items.add(item1);
        items.add(item2);

        cart.setItem1s(items);
//
//        CartNew cart2 = new CartNew() ;
//        cart2.setTotal(200);
//
//        items.remove(item1) ;
//        cart2.setItem1s(items);

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory() ;
        Session session = sessionFactory.openSession() ;

        Transaction tx= session.beginTransaction() ;

        session.save(cart) ;

//        session.save(cart2) ;

        tx.commit();
        session.close();
    }

}
