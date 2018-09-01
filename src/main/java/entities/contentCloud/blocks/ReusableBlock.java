package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import main.java.entities.contentCloud.folderItems.Folder;

import static main.java.utils.Generator.getRandomText;

public class ReusableBlock extends AbstractBlock{
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

    @JsonIgnore
    public String url;

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
        this.parentFolder = folder.id;
    }

    @JsonGetter("parentFolder")
    public String getParentFolder(){
        return parentFolder;
    }
}