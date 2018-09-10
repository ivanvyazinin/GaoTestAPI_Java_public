package main.java.entities.contentCloud.blocks.theory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Title extends AbstractBlock {
    public String title;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_THEORY + ENDPOINT_BLOCK_TITLE;

    public Title(){
        this.title=getRandomTextField("Title");
        this.position=0;
        this.type=1;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
