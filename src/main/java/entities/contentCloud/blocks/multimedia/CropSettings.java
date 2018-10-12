package main.java.entities.contentCloud.blocks.multimedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class CropSettings {
    public Integer width;
    public Integer height;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer x;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer y;

    public CropSettings(){}

    public CropSettings(int width, int height, int x, int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
}
