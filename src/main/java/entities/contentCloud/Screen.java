package main.java.entities.contentCloud;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomTextField;

public class Screen{
    public String name;
    public String description;
    public String parentFolder;
    @JsonIgnore
    public String id;
    @JsonIgnore
    public String createdAt;
    @JsonIgnore
    public String updatedAt;


    public Screen(String parent){
        this.name = getRandomTextField("Screen name");
        this.description = getRandomTextField("CI description");
        this.parentFolder = parent;
    }

    public Screen(String name, String description, String parent) {
        this.name = name;
        this.description = description;
        this.parentFolder = parent;
    }
    public Screen(){

    }
}
