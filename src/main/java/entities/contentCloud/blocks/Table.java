package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Table {
    public String table;
    public String screen;
    @JsonIgnore
    public String id;

    public Table(String table, String screen){
        this.table=table;
        this.screen=screen;
    }
}
