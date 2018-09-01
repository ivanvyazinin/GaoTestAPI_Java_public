package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentItem extends FolderItem {
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

    public ContentItem(){
        super();
    }
}
