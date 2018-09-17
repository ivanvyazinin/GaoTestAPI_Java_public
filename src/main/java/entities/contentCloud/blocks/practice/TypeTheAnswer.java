package main.java.entities.contentCloud.blocks.practice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class TypeTheAnswer extends CommonPracticeBlock {
    public String task;
    public String question;
    public List<String> answers;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_PRACTICE + ENDPOINT_BLOCK_ANSWER;

    public TypeTheAnswer(){
        this.task = getRandomTextRandomLength(1024);
        this.question = getRandomTextRandomLength(1024);
        this.showCorrectAnswer = true;
        this.position = 0;
        this.attempts = 2;
        this.answers = new ArrayList<>();
        this.scoring = new Scoring();
    }
    @Override
    public String getUrl() {
        return this.url;
    }
}
