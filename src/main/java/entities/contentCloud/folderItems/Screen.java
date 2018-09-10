package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;

import static main.java.properties.Endpoints.ENDPOINT_SCREENS;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url", "blocks"}, allowSetters= true)
public class Screen extends FolderItem {
    public static String url = ENDPOINT_SCREENS;

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

    @Override
    public String getUrl() {
        return this.url;
    }

    public ArrayList<AbstractBlock> blocks = new ArrayList<>();
}
