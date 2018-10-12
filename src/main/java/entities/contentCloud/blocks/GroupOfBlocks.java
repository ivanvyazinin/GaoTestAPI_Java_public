package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.core.Context;
import main.java.entities.AbstractEntity;
import main.java.entities.contentCloud.folderItems.ContentItem;

import java.util.ArrayList;

import static main.java.properties.Endpoints.ENDPOINT_GROUP_BLOCKS;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url","id"}, allowSetters=true)
public class GroupOfBlocks extends AbstractEntity {
    public String name;
    public String description;
    public String contentItem;
    public Integer color;
    public ArrayList<String> blocks;
    public static String url = ENDPOINT_GROUP_BLOCKS;

    public GroupOfBlocks(){
        blocks = new ArrayList<>();
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    public GroupOfBlocks(Context context){
        blocks = new ArrayList<>();
        color = 1;
        name = getRandomTextField("GroupOfBlocks");
        description = getRandomTextField("GroupOfBlocks description");
    }

    public GroupOfBlocks setCI(ContentItem contentItem){
        this.contentItem = contentItem.id;
        return this;
    }
}