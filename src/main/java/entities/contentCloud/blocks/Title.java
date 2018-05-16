package main.java.entities.contentCloud.blocks;

import static main.java.utils.Generator.getRandomTextField;

public class Title {
    public String title;
    public String screen;

    public Title(String title, String screen){
        this.title=title;
        this.screen=screen;
    }

    public Title(String screen){
        this.title=getRandomTextField("Title");
        this.screen=screen;
    }
}
