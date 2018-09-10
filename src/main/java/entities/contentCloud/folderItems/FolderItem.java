package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.AbstractEntity;

import static main.java.properties.Endpoints.ENDPOINT_FOLDER_ITEMS;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class FolderItem extends AbstractEntity {
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;
    public String parentFolder;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int type;
    public static String url = ENDPOINT_FOLDER_ITEMS;

    @JsonSetter("parentFolder")
    public void setParentFolder(Folder folder){
        if (folder == null) this.parentFolder = null;
        else this.parentFolder = folder.id;
    }

    @JsonGetter("parentFolder")
    public String getParentFolder(){
        return parentFolder;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
