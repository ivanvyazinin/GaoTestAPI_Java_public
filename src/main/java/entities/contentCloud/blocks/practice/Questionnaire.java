package main.java.entities.contentCloud.blocks.practice;

import java.util.ArrayList;
import java.util.List;

import static main.java.utils.Generator.getRandomTextRandomLength;

public class Questionnaire extends CommonPracticeBlock {
    public String task;
    public List<Question> questions;

    public Questionnaire(){
        this.task = getRandomTextRandomLength(1024);
        this.showCorrectAnswer = true;
        this.position = 0;
        this.attempts = 2;
        this.questions = new ArrayList<>();
        this.scoring = new Scoring();
    }

    public static class Question{
        public String question;
        public String answer_length;

        public Question(){
            this.question = getRandomTextRandomLength(1024);
            this.answer_length = "short";
        }

        public Question(String answer, String answer_length){
            this.question = answer;
            this.answer_length = answer_length;
        }
    }

}
