package main.java.entities.contentCloud;

import static main.java.utils.Generator.getRandomTextField;

public class Folder {
    public String name;
    public String parentFolder;

    public Folder(String parent){
        this.name = getRandomTextField("folder");
        this.parentFolder = parent;
    }

    public Folder(String name, String parent) {
        this.name = name;
        this.parentFolder = parent;
    }
}
