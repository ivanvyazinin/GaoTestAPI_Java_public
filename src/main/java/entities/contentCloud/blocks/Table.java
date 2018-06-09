package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Table {
    public String content;
    public String screen;
    @JsonIgnore
    public String id;

    public Table(String content, String screen){
        this.content=content;
        this.screen=screen;
    }
}
