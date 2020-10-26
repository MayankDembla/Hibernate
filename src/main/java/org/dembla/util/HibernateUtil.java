package org.dembla.util;

import org.dembla.model.Employee1;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtil {

    // XML Based Configuration
    private static SessionFactory sessionFactory;

    // Annotation based Configuration
    private static SessionFactory sessionAnnotationFactory;

    // property based configuration
    private static SessionFactory sessionJavaConfigFactory;

    /*
     * For the XMl based Configuration build the Session Factory
     */
    private static SessionFactory buildSessionFactory() {

        try {
            // create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addResource("employee.hbm.xml") ;
            System.out.println("Hibernate Configuration loaded.");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Service Registry created.");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Throwable exp) {
            System.err.println("Inital SessionFactory creation failed." + exp);
            throw new ExceptionInInitializerError(exp);
        }
    }


    /**
     * Method ued to buid the Session Factory for the Annotation based Configuration.
     * @return
     */
    private static SessionFactory buildSessionFactoryAnnotationFactory(){

        try{

            Configuration configuration = new Configuration() ;
            configuration.configure("hibernate-annotation.cfg.xml") ;
//            configuration.addAnnotatedClass(org.dembla.model.Employee.class) ;
            configuration.addAnnotatedClass(org.dembla.sessionsave.Emp.class) ;
            configuration.addAnnotatedClass(org.dembla.sessionsave.Address.class) ;
            configuration.addAnnotatedClass(org.dembla.mapping.onetoone.Customer.class) ;
            configuration.addAnnotatedClass(org.dembla.mapping.onetoone.Txn.class) ;
            System.out.println("Hibernate Annotation Coinfiguration loaded.");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build() ;
            System.out.println("Hibernate Annotation Service Registry created.");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry) ;

            return sessionFactory ;

        }catch (Throwable exp){
            System.err.println("Inital SessionFactory creation failed." + exp);
            throw new ExceptionInInitializerError(exp);
        }

    }

    /**
     * Build the Session Factory based on the Java Configuration
     *     here we use as hard fixed but we usually need to get these from the property file.
     * @return
     */

    private static SessionFactory buildSessionJavaConfigFactory(){

        try{
            Configuration configuration = new Configuration() ;

            // create =d files can be read from the property file too.
            Properties props = new Properties() ;

            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost/TestDB");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "admin");
            props.put("hibernate.current_session_context_class", "thread");


            configuration.setProperties(props) ;

            configuration.addAnnotatedClass(Employee1.class) ;


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build() ;

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry) ;

            return sessionFactory ;
        }
        catch (Throwable exp ){
            System.err.println("Initial SessionFactory creation failed, " + exp);
            throw new ExceptionInInitializerError(exp) ;
        }
    }


    // ############ Get the Session factory if not build already ########## //

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null)
            sessionFactory =  HibernateUtil.buildSessionFactory() ;

        return sessionFactory ;
    }

    public static SessionFactory getSessionAnnotationFactory(){

        if (sessionAnnotationFactory == null )
             sessionAnnotationFactory = HibernateUtil.buildSessionFactoryAnnotationFactory() ;

        return sessionAnnotationFactory ;
    }

    public static SessionFactory getSessionJavaConfigFactory(){
        if(sessionJavaConfigFactory == null)
            sessionJavaConfigFactory = HibernateUtil.buildSessionJavaConfigFactory() ;

        return sessionJavaConfigFactory ;
    }






}

