package main.java.entities.contentCloud;

import static main.java.utils.Generator.getRandomTextField;

public class Screen{
    public String name;
    public String description;
    public String parentFolder;

    public Screen(String parent){
        this.name = getRandomTextField("CI name");
        this.description = getRandomTextField("CI description");
        this.parentFolder = parent;
    }

    public Screen(String name, String description, String parent) {
        this.name = name;
        this.description = description;
        this.parentFolder = parent;
    }
}
