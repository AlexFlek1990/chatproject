package ru.af.messageService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHolder {

//    public final String DB_URL;
//    public final String USER_NAME;
//    public final String PASSWORD;


    private PropertyHolder() {
//        Properties properties = loadProperties();
//        DB_URL = properties.getProperty("url");
//        USER_NAME = properties.getProperty("username");
//        PASSWORD = properties.getProperty("password");
    }

    private static PropertyHolder instance;


    static Properties loadProperties() {
        Properties properties = null;

        InputStream inputStream = null;
        try {

            inputStream = new FileInputStream("src/main/resources/db-connection.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    public static PropertyHolder getProperties() {

        if (instance == null) {
            instance = new PropertyHolder();
        }
        return instance;
    }
}
