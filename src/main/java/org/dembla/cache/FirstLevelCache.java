package org.dembla.cache;

import org.dembla.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.stream.Stream;

public class FirstLevelCache {

    public static void main(String[] args) throws InterruptedException {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Animal animal = new Animal();
        animal.setName("Tiger");
        animal.setDescription("Calmness");


        Animal animal2 = new Animal();
        animal.setName("Cheetah");
        animal.setDescription("Fastest");


        session.save(animal);
        session.save(animal);
        session.save(animal);
        session.save(animal);
        session.save(animal);

        session.save(animal2);
        session.save(animal2);
        session.save(animal2);
        session.save(animal2);
        session.save(animal2);
        session.save(animal2);

        Animal oanimal = (Animal) session.load(Animal.class, 1);
        System.out.println(oanimal);

        Thread.sleep(1000);

        Animal oanimal1 = (Animal) session.load(Animal.class, 1);
        Animal oanimal2 = (Animal) session.load(Animal.class, 1);
        Animal oanimal3 = (Animal) session.load(Animal.class, 1);
        Animal oanimal4 = (Animal) session.load(Animal.class, 1);
        Animal oanimal5 = (Animal) session.load(Animal.class, 1);
        Animal oanimal6 = (Animal) session.load(Animal.class, 1);

        tx.commit();
        session.close();

        System.out.println("New Session begin ");

        Session sessionnew = sessionFactory.getCurrentSession();
        Transaction txnew = sessionnew.beginTransaction();

        Animal oanimal7 = (Animal) sessionnew.load(Animal.class, 1);
        Animal oanimal8 = (Animal) sessionnew.load(Animal.class, 1);
        Animal oanimal9 = (Animal) sessionnew.load(Animal.class, 1);
        Animal oanimal10 = (Animal) sessionnew.load(Animal.class, 1);
        Animal oanimal11 = (Animal) sessionnew.load(Animal.class, 1);
        Animal oanimal12 = (Animal) sessionnew.load(Animal.class, 1);

        System.out.println(oanimal7);
        tx.commit();
        sessionnew.close();
    }

}
