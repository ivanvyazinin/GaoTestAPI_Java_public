package main.java.core;

import org.aeonbits.owner.ConfigFactory;

public class Configuration {
    public static void main(String[] args) {
        System.out.println(getServerConfig().getHost());
    }

    public static ServerConfig getServerConfig(){
        return ConfigFactory.newInstance().create(ServerConfig.class);
    }
}
