package main.java.entities.directories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static main.java.properties.Endpoints.*;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Skill extends AbstractDirectory {
    public static String url = ENDPOINT_DIRECTORY + ENDPOINT_DIRECTORY_SKILL;

    public String getUrl(){
        return this.url;
    }
}
