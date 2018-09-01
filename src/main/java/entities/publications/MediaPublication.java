package main.java.entities.publications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaPublication {
    public String name;
    public String description;
    public String language;
    public String license;
    public ArrayList studies;

    @JsonIgnore
    public String id;

    public MediaPublication(){
        studies = new ArrayList();
    }
}
