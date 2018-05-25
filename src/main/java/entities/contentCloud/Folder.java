package main.java.entities.contentCloud;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomTextField;

public class Folder {
    public String name;
    public String parentFolder;
    @JsonIgnore
    public String id;
    @JsonIgnore
    public String updatedAt;
    @JsonIgnore
    public String createdAt;

    public Folder(String parent){
        this.name = getRandomTextField("folder");
        this.parentFolder = parent;
    }

    public Folder(String name, String parent) {
        this.name = name;
        this.parentFolder = parent;
    }

    public Folder(){

    }
}
