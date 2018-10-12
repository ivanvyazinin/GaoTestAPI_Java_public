package main.java.core;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:${env}.properties", "classpath:cc.properties"})
public interface ServerConfig extends Config{
    @Key("server.host")
    String getHost();

    @Key("db.host")
    String getDbHost();
}
