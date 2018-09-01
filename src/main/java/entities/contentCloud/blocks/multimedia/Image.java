package main.java.entities.contentCloud.blocks.multimedia;

import main.java.entities.contentCloud.blocks.ReusableBlock;

import java.util.ArrayList;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class Image extends ReusableBlock {
    String captionText;
    ArrayList<String> files;

    public Image(){
        captionText = getRandomTextRandomLength(512);
        files = new ArrayList<>();
        url = "multimedia/image";
    }
}