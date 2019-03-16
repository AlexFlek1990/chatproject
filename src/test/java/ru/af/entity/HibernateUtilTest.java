package ru.af.entity;

import org.junit.Before;
import org.junit.Test;
import ru.af.entitymanager.EntityManagerUtil;

import static junit.framework.TestCase.assertEquals;

public class HibernateUtilTest {

    private EntityManagerUtil entityManagerUtil = new EntityManagerUtil();


    @Before
    public void before() {
        entityManagerUtil.deleteAllMessages();
    }

//    @Test
//    public void insertUser() {
//        User user = new User();
//        user.setName("name");
//        Integer id = entityManagerUtil.insertUser(user);
//        assertEquals(user, entityManagerUtil.getUserById(id));
//    }


    @Test
    public void insertMessage() {
        Message message = new Message();
        Integer id = entityManagerUtil.insertMessage(message);
        assertEquals(message, entityManagerUtil.getMessageById(id));
    }

//    @Test
//    public void updateUser() {
//        User user = new User();
//        String s = "newName";
//        user.setName("name");
//        user.setName("userName");
//        Integer id = entityManagerUtil.insertUser(user);
//        entityManagerUtil.updateUser(user,s);
//        user.setName(s);
//        assertEquals(user, entityManagerUtil.getUserById(id));
//    }

    @Test
    public void updateMessage() {
        Message message = new Message();
        message.setBody("body");
        Integer id = entityManagerUtil.insertMessage(message);
        String s = "newBody";
        entityManagerUtil.updateMessage(message,s);
        message.setBody(s);
        assertEquals(message, entityManagerUtil.getMessageById(id));
    }

//    @Test
//    public void deleteUser() {
//        User user = new User();
//        Integer id = entityManagerUtil.insertUser(user);
//        user.setId(id);
//        //возможно удобнее было бы реализовать метод удаления по id, чтобы не создавать лишний фейковый объект User
//        entityManagerUtil.deleteUser(user);
//        assertEquals(null, entityManagerUtil.getUserById(id));
//
//    }

    @Test
    public void deleteMessage() {
        Message message = new Message();
        Integer id = entityManagerUtil.insertMessage(message);
        message.setId(id);
        entityManagerUtil.deleteMessage(message);
        assertEquals(null, entityManagerUtil.getMessageById(id));

    }


}