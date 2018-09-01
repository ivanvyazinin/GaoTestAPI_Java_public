package main.java.entities.contentCloud.blocks.practice;

import java.util.ArrayList;
import java.util.List;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class CategorizeWords extends CommonPracticeBlock {
    public String task;
    public List<Category> category;

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

}
