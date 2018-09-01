package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

public abstract class FolderItem {
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;
    public String parentFolder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String id;

    @JsonSetter("parentFolder")
    public void setParentFolder(Folder folder){
        this.parentFolder = folder.id;
    }

    @JsonGetter("parentFolder")
    public String getParentFolder(){
        return parentFolder;
    }

}
