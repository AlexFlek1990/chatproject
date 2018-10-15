package ru.af.messageService;
// В java Naming Conventions говорится, что имена пакетов пишутся маленькими буквами
// https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html

public class HibernateMain {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setName("User1");
        Message m1 = new Message();
        m1.setBody("text of m1");
        m1.setId(3);
        m1.setTime(1537033429);
        m1.setUserId(1);

 //       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        HibernateUtil.insertUser(u1, sessionFactory);
//        HibernateUtil.insertMessage(m1, sessionFactory);
//        System.out.println(HibernateUtil.getMessageById(1,sessionFactory));
//        System.out.println(HibernateUtil.getUserById(1, sessionFactory));
//        HibernateUtil.updateMessage(m1,"edited body",sessionFactory);
//        HibernateUtil.deleteMessage(m1,sessionFactory);

//        HibernateUtil.updateUser(u1, "newName", sessionFactory);
//        System.out.println(HibernateUtil.getMessageById(1,sessionFactory));

//        sessionFactory.close();
//

//        User u = HibernateUtil.getUserById(1);
//        HibernateUtil.insert(u1);
//        HibernateUtil.getUserById(1);
//        System.out.println(u1);

    }
}
