package jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class DataBaseCredential {

    private String URL;
    private String USERNAME;
    private String PASSWORD;


    //Implementing the singleton design pattern in this class to ease the access the content and preventing the over producing the objects
    private static DataBaseCredential INSTANCE;

    public static DataBaseCredential getINSTANCE() {
        if (INSTANCE == null)
            throw new IllegalArgumentException("Initializing is indispensable , please create an instance");

        return INSTANCE;
    }


    private void loadProperties(Path path) {
        Properties properties = new Properties();

        try {
            properties.load(Files.newBufferedReader(path));
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getURL() {
        return URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}



