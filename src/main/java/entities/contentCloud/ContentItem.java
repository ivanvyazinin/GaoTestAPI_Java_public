package main.java.entities.contentCloud;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomTextField;

public class ContentItem {
    public String name;
    public String description;
    public String parentFolder;
    @JsonIgnore
    public String id;


    public ContentItem(String parent){
        this.name = getRandomTextField("CI name");
        this.description = getRandomTextField("CI description");
        this.parentFolder = parent;
    }

    public ContentItem(String name, String description, String parent) {
        this.name = name;
        this.description = description;
        this.parentFolder = parent;
    }
}
