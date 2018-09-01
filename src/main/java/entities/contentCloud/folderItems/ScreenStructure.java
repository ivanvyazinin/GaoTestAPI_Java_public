package main.java.entities.contentCloud.folderItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreenStructure {
    public ArrayList<AbstractBlock> items;

    public ScreenStructure(){
        items = new ArrayList<>();
    }
}
