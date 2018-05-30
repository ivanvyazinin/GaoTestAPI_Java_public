package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BulletList {
    public String bulletList;
    public String screen;
    @JsonIgnore
    public String id;

    public BulletList(String bulletList, String screen){
        this.bulletList=bulletList;
        this.screen=screen;
    }
}
