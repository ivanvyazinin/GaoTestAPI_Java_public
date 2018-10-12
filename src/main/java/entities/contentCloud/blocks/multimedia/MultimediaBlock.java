package main.java.entities.contentCloud.blocks.multimedia;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.contentCloud.Files;
import main.java.entities.contentCloud.blocks.ReusableBlock;

import java.util.ArrayList;

public abstract class MultimediaBlock extends ReusableBlock {
    public ArrayList<String> files;
    public String captionText;

    @JsonSetter("files")
    public void setFiles(ArrayList<Files> files){
        for (Files file: files){
            this.files.add(file.id);
        }
    }

    @JsonGetter("files")
    public ArrayList<String> getFiles(){
        return files;
    }

}
