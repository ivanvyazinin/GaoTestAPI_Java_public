package main.java.entities.contentCloud.blocks;

import static main.java.utils.Generator.getRandomTextField;

public class Subtitle {
    public String subtitle;
    public String screen;

    public Subtitle(String subtitle, String screen){
        this.subtitle=subtitle;
        this.screen=screen;
    }

    public Subtitle(String screen){
        this.subtitle=getRandomTextField("Subtitle");
        this.screen=screen;
    }
}
