package main.java.entities.contentCloud.blocks.theory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Subtitle extends AbstractBlock {
    public String subtitle;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_THEORY + ENDPOINT_BLOCK_SUBTITLE;
    @Override
    public String getUrl() {
        return this.url;
    }

    public Subtitle(){
        this.subtitle=getRandomTextField("Subtitle");
    }

}
