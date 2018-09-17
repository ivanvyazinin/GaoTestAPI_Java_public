package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import main.java.entities.contentCloud.folderItems.Folder;

import static main.java.utils.Generator.getRandomText;

public abstract class ReusableBlock extends AbstractBlock{
    @JsonInclude(Include.NON_NULL)
    public String name;
    @JsonInclude(Include.NON_NULL)
    public String description;
    @JsonInclude(Include.NON_NULL)
    public boolean reusable;
    @JsonInclude(Include.NON_NULL)
    public String level;
    @JsonInclude(Include.NON_NULL)
    private String parentFolder;

    public  ReusableBlock(String parentFolder, String level){
        this.reusable = true;
        this.name = getRandomText(100);
        this.parentFolder = parentFolder;
        this.level = level;
    }

    public  ReusableBlock(){

    }

    @JsonSetter("parentFolder")
    public void setParentFolder(Folder folder){
        if (folder == null) this.parentFolder = null;
        else this.parentFolder = folder.id;
    }

    public void setFolder(String folderId){
        this.parentFolder = folderId;
    }

    @JsonGetter("parentFolder")
    public String getParentFolder(){
        return parentFolder;
    }

    @Override
    public abstract String getUrl();
}