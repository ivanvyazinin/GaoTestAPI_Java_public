package main.java.entities.contentCloud;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Publication {
    @JsonIgnore
    public String id;
    @JsonIgnore
    public String updatedAt;
    @JsonIgnore
    public String createdAt;

    public String name;
    public String description;
    public String level;
    public String language;
    public String zone;
    public String eqf;
    public String license;
    public String studies;
    public String skills;
    public String isco;

    public Publication(){

    }

}
