package main.java.entities.directories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY;
import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY_EQF;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Eqf extends AbstractDirectory{
    public static String url = ENDPOINT_DIRECTORY + ENDPOINT_DIRECTORY_EQF;

    public String getUrl(){
        return this.url;
    }
}
