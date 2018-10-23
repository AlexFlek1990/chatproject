package ru.af.entity;

import org.junit.Before;
import org.junit.Test;
import ru.af.hibernate.HibernateUtil;

import static junit.framework.TestCase.assertEquals;

public class HibernateUtilTest {

    private HibernateUtil hibernateUtil = new HibernateUtil();


    @Before
    public void before() {
        hibernateUtil.deleteAllMessages();
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("name");
        Integer id = hibernateUtil.insertUser(user);
        assertEquals(user,hibernateUtil.getUserById(id));
    }


    @Test
    public void insertMessage() {
        Message message = new Message();
        Integer id =hibernateUtil.insertMessage(message);
        assertEquals(message, hibernateUtil.getMessageById(id));
    }

    @Test
    public void updateUser() {
        User user = new User();
        String s = "newName";
        user.setName("name");
        user.setName("userName");
        Integer id = hibernateUtil.insertUser(user);
        hibernateUtil.updateUser(user,s);
        user.setName(s);
        assertEquals(user,hibernateUtil.getUserById(id));

    }

    @Test
    public void updateMessage() {
        Message message = new Message();
        message.setBody("body");
        Integer id = hibernateUtil.insertMessage(message);
        String s = "newBody";
        hibernateUtil.updateMessage(message,s);
        message.setBody(s);
        assertEquals(message,hibernateUtil.getMessageById(id));
    }

    @Test
    public void deleteUser() {
        User user = new User();
        Integer id =hibernateUtil.insertUser(user);
        user.setId(id);
        //возможно удобнее было бы реализовать метод удаления по id, чтобы не создавать лишний фейковый объект User
        hibernateUtil.deleteUser(user);
        assertEquals(null,hibernateUtil.getUserById(id));

    }

    @Test
    public void deleteMessage() {
        Message message = new Message();
        Integer id =hibernateUtil.insertMessage(message);
        message.setId(id);
        hibernateUtil.deleteMessage(message);
        assertEquals(null,hibernateUtil.getMessageById(id));

    }


}