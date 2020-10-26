package org.dembla.mapping.onetomany;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class HibernateOneToManyMain {

    public static void main(String[] args) {

        Cart cart = new Cart("MyCart") ;

        Items item1 = new Items("I10", 10, 1, cart);
        Items item2 = new Items("I20", 20, 2, cart);

        Set<Items> itemsSet = new HashSet<Items>();
        itemsSet.add(item1);
        itemsSet.add(item2);

        cart.setItems(itemsSet);
        cart.setTotal(10*1 + 20*2);

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory() ;
        Session session = sessionFactory.openSession() ;
        Transaction transaction = session.beginTransaction() ;

        session.save(cart) ;
        session.save(item1);
        session.save(item2);
        transaction.commit();

        session.close();
    }
}
