package main.java.entities.contentCloud.blocks.multimedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Audio extends MultimediaBlock {
    public String captionText;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_MULTIMEDIA + ENDPOINT_BLOCK_AUDIO;

    public Audio(){
        captionText = getRandomTextRandomLength(512);
        files = new ArrayList<>();
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}