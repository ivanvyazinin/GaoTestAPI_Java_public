package main.java.entities.contentCloud.blocks.theory;

import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;

public class BulletList extends AbstractBlock {
    public ArrayList<String> bulletList;

    public BulletList(){
        this.bulletList = new ArrayList<>();
    }
}
