package main.java.entities.directories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY;
import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY_ZONES;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Zone extends AbstractDirectory{
    public static String url = ENDPOINT_DIRECTORY + ENDPOINT_DIRECTORY_ZONES;

    public String getUrl(){
        return this.url;
    }
}
