package ru.af.entitymanager;

import ru.af.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static  final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.
            createEntityManagerFactory("JavaHelps");
    /*
    метод нихерища не возвращает. и не узнать записал ли он что-то в бд или нет
     */
    public static int insertMessage(Message message){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(message);
            transaction.commit();
        } catch (Exception ex){

        } finally {
            entityManager.close();
        }
        return message.getId();
    }

    public static Message getMessageById(int id){
        Message message = null;
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try{
            transaction.begin();
            message = entityManager.
                    createQuery("select * from message where id="+id,Message.class).getSingleResult();
            transaction.commit();
        }catch(Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally{
            entityManager.close();
        }
        return message;
    }

    public static void updateMessage(Message message, String newBody){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        message.setBody(newBody);
        try{
            transaction = entityManager.getTransaction();
            Message updatedMessage = entityManager.find(Message.class,message);
            transaction.begin();
            updatedMessage.setBody(newBody);
            entityManager.persist(updatedMessage);
            transaction.commit();
        } catch (Exception e){

        }finally {
            entityManager.close();
        }
//        return updateMessage()
    }

    public static  void deleteMessage(Message message){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            Message message1 = entityManager.find(Message.class,message);
            entityManager.remove(message1);
            transaction.commit();
            } catch(Exception e){
            e.printStackTrace();
        } finally{
            entityManager.close();
        }
    }

    public static void deleteAllMessages(){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.createQuery("delete from message").executeUpdate();
            transaction.commit();
        }catch(Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally{
            entityManager.close();
        }

    }




}
