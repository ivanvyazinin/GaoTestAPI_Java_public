package main.java.entities.contentCloud.blocks.theory;

import main.java.entities.contentCloud.blocks.AbstractBlock;

import static main.java.utils.Generator.getRandomTextField;

public class Example extends AbstractBlock {
    public String example;

    public Example(String example){
        this.example=example;
    }

    public Example(){
        this.example=getRandomTextField("Example");
    }
}
