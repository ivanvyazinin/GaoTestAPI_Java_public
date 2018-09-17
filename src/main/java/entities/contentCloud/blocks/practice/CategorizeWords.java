package main.java.entities.contentCloud.blocks.practice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class CategorizeWords extends CommonPracticeBlock {
    public String task;
    public List<Category> category;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_PRACTICE + ENDPOINT_BLOCK_CATEGORIZE_WORDS;

    public CategorizeWords(){
        this.task = getRandomTextRandomLength(512);
        this.showCorrectAnswer = true;
        this.position = 0;
        this.attempts = 2;
        this.category = new ArrayList<>();
        this.scoring = new Scoring();
    }

    public static class Category {
        public String name;
        public ArrayList<String> items;

        public Category(){
            this.name = getRandomTextRandomLength(160);
            this.items = new ArrayList<>();
            this.items.add(getRandomTextRandomLength(160));
            this.items.add(getRandomTextRandomLength(160));
        }

    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
