package main.java.entities.directories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY;
import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY_LANGUAGE;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Language extends AbstractDirectory{
    public static String url = ENDPOINT_DIRECTORY + ENDPOINT_DIRECTORY_LANGUAGE;

    public String getUrl(){
        return this.url;
    }
}
