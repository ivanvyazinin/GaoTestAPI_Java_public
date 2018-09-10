package main.java.entities.contentCloud.blocks.theory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import static main.java.properties.Endpoints.*;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Example extends AbstractBlock {
    public String example;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_THEORY + ENDPOINT_BLOCK_EXAMPLE;

    public Example(){
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
