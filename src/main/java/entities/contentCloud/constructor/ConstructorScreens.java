package main.java.entities.contentCloud.constructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomCoordinate;

public class ConstructorScreens {
    public int positionX;
    public int positionY;
    public String screen;
    public String parentContentItem;

    @JsonIgnore
    public String createdAt;
    @JsonIgnore
    public String updatedAt;


    public ConstructorScreens(String screen, String parentContentItem){
        this.positionX= getRandomCoordinate();
        this.positionY= getRandomCoordinate();
        this.screen=screen;
        this.parentContentItem=parentContentItem;
    }
}
