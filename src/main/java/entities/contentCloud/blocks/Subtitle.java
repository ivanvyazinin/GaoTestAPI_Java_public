package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomTextField;

public class Subtitle {
    public String subtitle;
    public String screen;
    @JsonIgnore
    public String id;


    public Subtitle(String subtitle, String screen){
        this.subtitle=subtitle;
        this.screen=screen;
    }

    public Subtitle(String screen){
        this.subtitle=getRandomTextField("Subtitle");
        this.screen=screen;
    }
}
