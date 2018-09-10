package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.AbstractEntity;

import java.util.ArrayList;

import static main.java.properties.Endpoints.ENDPOINT_GROUP_BLOCKS;

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
}