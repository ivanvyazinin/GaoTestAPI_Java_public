package main.java.entities.contentCloud.blocks.theory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.ReusableBlock;

import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Paragraph extends ReusableBlock {
    public String paragraph;

    public Paragraph(String paragraph){
        this.paragraph=paragraph;
        this.type=3;
        url = "theory/paragraph";
    }

    public Paragraph(String parentFolder, String level){
        super(parentFolder, level);
        this.paragraph=getRandomTextField("Paragraph");
        this.type=3;
        url = "theory/paragraph";
    }

    public Paragraph(){
        this.paragraph=getRandomTextField("Paragraph");
        this.type=3;
        url = "theory/paragraph";
    }
}
