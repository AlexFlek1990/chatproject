
package ru.af.messageService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtil {


    private SessionFactory sessionAnnotationFactory;


    private SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

//            Properties props = PropertyHolder.loadProperties();
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost/chat");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "");
            props.put("hibernate.current_session_context_class", "thread");

            configuration.setProperties(props);

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Message.class);

            ServiceRegistry serviceRegistry = new
                    StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        if (sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionJavaConfigFactory();
        return sessionAnnotationFactory;
    }


     User getUserById(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        transaction.commit();
        return user;
    }


    Message getMessageById(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Message message = (Message) session.get(Message.class, id);
        transaction.commit();
        return message;
    }

    Integer insertUser(User user) {
            Session session = getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer)session.save(user);
            transaction.commit();
            return id;
    }
    Integer insertMessage(Message message) {
            Session session = getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer)session.save(message);
            transaction.commit();
            return id;
    }

    void updateUser(User user, String newName) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        user.setName(newName);
        session.update(user);
        transaction.commit();
    }
    void updateMessage(Message message, String newBody) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        message.setBody(newBody);
        session.update(message);
        transaction.commit();
    }

    void deleteUser(User user){
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction= session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }

    void deleteMessage(Message message){
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction= session.beginTransaction();
        session.delete(message);
        transaction.commit();
    }

    void deleteAllMessages() {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction= session.beginTransaction();
        Query query = session.createQuery("delete from Message");
        query.executeUpdate();
        transaction.commit();
    }

}


