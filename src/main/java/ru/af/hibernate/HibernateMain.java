package ru.af.hibernate;
// В java Naming Conventions говорится, что имена пакетов пишутся маленькими буквами
// https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html

import ru.af.entity.Message;
import ru.af.hibernate.HibernateUtil;

public class HibernateMain {
    public static void main(String[] args) {
//        User u1 = new User();
//        u1.setId(1);
//        u1.setName("User1");


        Message m1 = new Message();
        m1.setBody("text of m1");
        m1.setId(3);
        m1.setTime(1537033429);
        m1.setUserId(1);
        HibernateUtil hu = new HibernateUtil();
        hu.insertMessage(m1);


//        hu.insertUser(u1);
//        hu.insertMessage(m1);
//        System.out.println(hu.getMessageById(1));
//        System.out.println(HibernateUtil.getUserById(1, sessionFactory));
//        HibernateUtil.updateMessage(m1,"edited body",sessionFactory);
//        HibernateUtil.deleteMessage(m1,sessionFactory);
//
//        HibernateUtil.updateUser(u1, "newName", sessionFactory);
//        System.out.println(HibernateUtil.getMessageById(1,sessionFactory));
//
//        sessionFactory.close();


//        User u = HibernateUtil.getUserById(1);
//        HibernateUtil.insert(u1);
//        HibernateUtil.getUserById(1);
//        System.out.println(u1);

    }
}
