package main.java.entities.contentCloud.blocks.theory;

import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;

public class ScreenBlocks <T extends AbstractBlock> {
        public int count;
        public ArrayList <T> items;

        public T getItem(int index){
            return items.get(index);
        }
}