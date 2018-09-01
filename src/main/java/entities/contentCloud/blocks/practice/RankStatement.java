package main.java.entities.contentCloud.blocks.practice;

import java.util.ArrayList;
import java.util.List;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class RankStatement extends CommonPracticeBlock {
    public String task;
    public List<String> statements;

    public RankStatement(){
        this.task = getRandomTextRandomLength(512);
        this.showCorrectAnswer = true;
        this.position = 0;
        this.attempts = 2;
        this.statements = new ArrayList<>();
        this.scoring = new Scoring();
    }

}
