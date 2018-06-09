package main.java.entities.contentCloud.publications;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class ContentPublication {
    public String name;
    public String description;
    public String level;
    public String language;
    public String zone;
    public String eqf;
    public String license;
    public ArrayList studies;
    public ArrayList skills;
    public ArrayList isco;

    @JsonIgnore
    public String id;

    public ContentPublication(){
        studies = new ArrayList();
        skills = new ArrayList();
        isco = new ArrayList();
    }
}
