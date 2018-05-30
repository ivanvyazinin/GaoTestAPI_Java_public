package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomTextField;

public class Paragraph {
    public String paragraph;
    public String screen;
    @JsonIgnore
    public String id;

    public Paragraph(String paragraph, String screen){
        this.paragraph=paragraph;
        this.screen=screen;
    }

    public Paragraph(String screen){
        this.paragraph=getRandomTextField("Paragraph");
        this.screen=screen;
    }
}
