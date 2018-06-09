package main.java.entities.contentCloud.publications;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

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
