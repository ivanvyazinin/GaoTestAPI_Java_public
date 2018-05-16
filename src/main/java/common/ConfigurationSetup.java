package main.java.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ConfigurationSetup {
    public Environment environment;
    private Properties environmentProps;
    private Properties applicationProps;

    public ConfigurationSetup() {
        getProperties();
        environment = getEnv("qa");
    }

    public static void main(String[] args) {
        ConfigurationSetup cs = new ConfigurationSetup();
        System.out.println(cs.environment.toString());
    }

    private Environment getEnv(String envName) {
        String host = environmentProps.getProperty(String.format("%s.host", envName));
        return new Environment(host);
    }

    private void getProperties() {
        environmentProps = new Properties();
        applicationProps = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream("environment.properties");
            environmentProps.load(input);
            input = getClass().getClassLoader().getResourceAsStream("common.properties");
            applicationProps.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
