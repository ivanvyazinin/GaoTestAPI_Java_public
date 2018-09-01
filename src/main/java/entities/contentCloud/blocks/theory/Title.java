package main.java.entities.contentCloud.blocks.theory;

import main.java.entities.contentCloud.blocks.AbstractBlock;

import static main.java.utils.Generator.getRandomTextField;

public class Title extends AbstractBlock {
    public String title;

    public Title(String title, String screen, int position){
        this.title=title;
        this.position=position;
        this.type=1;
    }

    public Title(){
        this.title=getRandomTextField("Title");
        this.position=0;
        this.type=1;
    }


}
