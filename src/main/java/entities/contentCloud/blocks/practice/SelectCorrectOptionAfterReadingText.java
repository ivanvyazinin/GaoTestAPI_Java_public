package main.java.entities.contentCloud.blocks.practice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class SelectCorrectOptionAfterReadingText extends SelectCorrectOption {
    public String text;
    public List<QuestionsWithAnswer> questionsWithAnswers;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_PRACTICE + ENDPOINT_BLOCK_SELECT_CORRECT_OPTION_AFTER_READING;

    public SelectCorrectOptionAfterReadingText(){
        this.task = getRandomTextRandomLength(512);
        this.showCorrectAnswer = true;
        this.mixAnswers = true;
        this.position = 0;
        this.attempts = 2;
        this.answers = new ArrayList<>();
        this.scoring = new Scoring();
        this.text = getRandomTextRandomLength(1000);
        this.questionsWithAnswers = new ArrayList<>();

    }

    public static class QuestionsWithAnswer{
        public String question;
        public List<Answer> answers;

        public QuestionsWithAnswer(){
            question = getRandomTextRandomLength(512);
            answers = new ArrayList<>();
            answers.add(new SelectCorrectOption.Answer());
            answers.add(new SelectCorrectOption.Answer());
        }
    }
    @Override
    public String getUrl() {
        return this.url;
    }
}
