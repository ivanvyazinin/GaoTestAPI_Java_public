package main.java.entities.contentCloud.blocks.theory;

import main.java.entities.contentCloud.blocks.AbstractBlock;

import static main.java.utils.Generator.getRandomTextField;

public class Subtitle extends AbstractBlock {
    public String subtitle;

    public Subtitle(String subtitle){
        this.subtitle=subtitle;
    }

    public Subtitle(){
        this.subtitle=getRandomTextField("Subtitle");
    }
}
