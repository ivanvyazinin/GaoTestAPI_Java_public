package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomTextField;

public class Example {
    public String example;
    public String screen;
    @JsonIgnore
    public String id;

    public Example(String example, String screen){
        this.example=example;
        this.screen=screen;
    }

    public Example(String screen){
        this.example=getRandomTextField("Example");
        this.screen=screen;
    }
}
