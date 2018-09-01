package main.java.entities.contentCloud.blocks.practice;

import java.util.ArrayList;
import java.util.List;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class SelectCorrectOption extends CommonPracticeBlock {
    public String task;
    public Boolean mixAnswers;
    public List<Answer> answers;

    public SelectCorrectOption(){
        this.task = getRandomTextRandomLength(512);
        this.showCorrectAnswer = true;
        this.mixAnswers = true;
        this.position = 0;
        this.attempts = 2;
        this.answers = new ArrayList<>();
        this.scoring = new Scoring();
    }

    public static class Answer{
        public String answer;
        public boolean correct;

        public Answer(){
            this.answer = getRandomTextRandomLength(1024);
            this.correct = true;
        }

        public Answer(String answer, boolean correct){
            this.answer = answer;
            this.correct = correct;
        }
    }

}
