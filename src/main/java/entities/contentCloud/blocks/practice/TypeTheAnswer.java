package main.java.entities.contentCloud.blocks.practice;

import java.util.ArrayList;
import java.util.List;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class TypeTheAnswer extends CommonPracticeBlock {
    public String task;
    public String question;
    public List<String> answers;

    public TypeTheAnswer(){
        this.task = getRandomTextRandomLength(1024);
        this.question = getRandomTextRandomLength(1024);
        this.showCorrectAnswer = true;
        this.position = 0;
        this.attempts = 2;
        this.answers = new ArrayList<>();
        this.scoring = new Scoring();
    }

}
