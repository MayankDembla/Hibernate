package org.dembla.mapping.onetoone;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class TestOnetoOne {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory() ;
        Session session = sessionFactory.openSession() ;

        // First Transaction t1 -> c1
        Transaction tx = session.beginTransaction() ;
        System.out.println("Session created using annotations configuration");
        Txn txn = buildDemoTransaction();
        session.save(txn) ;
        tx.commit();

        // Second Transaction t2,t3,... -> c1
        for(int i = 0 ; i < 5 ; i++) {
            Transaction tx1 = session.beginTransaction();
            System.out.println("Session created using annotations configuration");
            Txn txn1 = buildDemoTransactionbreak();
            session.save(txn1);
            tx1.commit();
        }



        session.close();
    }
    static Txn txn = new Txn();
    static Customer cust = new Customer();
    private static Txn buildDemoTransaction() {

        txn.setDate(new Date());
        txn.setTotal(100);


        cust.setAddress("San Jose, USA");
        cust.setEmail("pankaj@yahoo.com");
        cust.setName("Pankaj Kr");

        txn.setCustomer(cust);

        cust.setTxn(txn);
        return txn;
    }
    private static Txn buildDemoTransactionbreak() {

        Txn txn = new Txn();
        txn.setDate(new Date());
        txn.setTotal(100);

        cust.setAddress("San m, IND");
        cust.setEmail("mn@yahoo.com");
        cust.setName("mn Kr");

        txn.setCustomer(cust);

        cust.setTxn(txn);
        return txn;
    }
}
