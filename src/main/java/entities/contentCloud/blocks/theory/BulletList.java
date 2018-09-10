package main.java.entities.contentCloud.blocks.theory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;

import static main.java.properties.Endpoints.*;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class BulletList extends AbstractBlock {
    public ArrayList<String> bulletList;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_THEORY + ENDPOINT_BLOCK_BULLETLIST;

    public BulletList(){
        this.bulletList = new ArrayList<>();
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
