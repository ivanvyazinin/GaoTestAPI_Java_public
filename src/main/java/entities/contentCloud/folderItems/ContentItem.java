package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

import static main.java.properties.Endpoints.ENDPOINT_CONTENT_ITEMS;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url", "screens"}, allowSetters= true)
public class ContentItem extends FolderItem {
    public static String url = ENDPOINT_CONTENT_ITEMS;

    public ContentItem(String parent){
        this.name = getRandomTextField("CI name");
        this.description = getRandomTextField("CI description");
        this.parentFolder = parent;
    }

    public ContentItem(){
        super();
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    public ArrayList<Screen> screens = new ArrayList<>();
}
