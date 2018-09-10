package main.java.entities.contentCloud.blocks.structure;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.contentCloud.File;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class WelcomeExit extends AbstractBlock {
    public String title;
    public String subtitle;
    public String description;
    public ArrayList<String> files;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_STRUCTURE + ENDPOINT_BLOCK_WELCOME_EXIT;

    public WelcomeExit(){
        title = getRandomTextRandomLength(160);
        subtitle = getRandomTextRandomLength(512);
        description = getRandomTextRandomLength(1024);
        files = new ArrayList<>();

        type = 18;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @JsonSetter("files")
    public void setFiles(ArrayList<File> files){
        for (File file: files){
            this.files.add(file.id);
        }
    }

    @JsonGetter("files")
    public ArrayList<String> getFiles(){
        return files;
    }

}