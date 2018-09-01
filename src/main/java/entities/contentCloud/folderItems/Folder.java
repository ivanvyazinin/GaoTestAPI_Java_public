package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Folder extends FolderItem {
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

    @JsonSetter("parentFolder")
    public void setParentFolder(String folder){
        this.parentFolder = folder;
    }
}
