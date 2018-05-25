package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomTextField;

public class Title {
    public String title;
    public String screen;
    public int position;
    @JsonIgnore
    public String id;


    public Title(String title, String screen, int position){
        this.title=title;
        this.screen=screen;
        this.position=position;
    }

    public Title(String screen){
        this.title=getRandomTextField("Title");
        this.screen=screen;
        this.position=0;
    }
}
