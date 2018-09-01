package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Screen extends FolderItem {

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
        super();
    }
}
