
package ru.af.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.af.entity.Message;
import ru.af.entity.User;

import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {
    try {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Message.class);
        configuration.addAnnotatedClass(User.class);



        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        System.out.println("Hibernate Java Config serviceRegistry created");

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        return sessionFactory;
    } catch (Throwable ex) {
        ex.printStackTrace();
        throw new ExceptionInInitializerError(ex);
    }
}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }


    public User getUserById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        transaction.commit();
        return user;
    }


    public Message getMessageById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Message message = (Message) session.get(Message.class, id);
        transaction.commit();
        return message;
    }

    public Integer insertUser(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(user);
        transaction.commit();
        return id;
    }

    public Integer insertMessage(Message message) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(message);
        transaction.commit();
        return id;
    }

    public void updateUser(User user, String newName) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        user.setName(newName);
        session.update(user);
        transaction.commit();
    }

    public void updateMessage(Message message, String newBody) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        message.setBody(newBody);
        session.update(message);
        transaction.commit();
    }

    public void deleteUser(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }

    public void deleteMessage(Message message) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(message);
        transaction.commit();
    }

    public void deleteAllMessages() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Message");
        query.executeUpdate();
        transaction.commit();
    }

    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> result = query.list();
        transaction.commit();
        return result;
    }

    public List<Message> getAllMessages() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Message");
        List<Message> result = query.list();
        transaction.commit();
        return result;
    }
}