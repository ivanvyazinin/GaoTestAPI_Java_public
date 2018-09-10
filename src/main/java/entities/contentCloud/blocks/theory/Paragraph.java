package main.java.entities.contentCloud.blocks.theory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.ReusableBlock;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Paragraph extends ReusableBlock {
    public String paragraph;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_THEORY + ENDPOINT_BLOCK_PARAGRAPH;

    public Paragraph(String parentFolder, String level){
        super(parentFolder, level);
        this.paragraph=getRandomTextField("Paragraph");
        this.type=3;
    }

    public Paragraph(){
        this.type=3;
    }
    @Override
    public String getUrl() {
        return this.url;
    }
}
