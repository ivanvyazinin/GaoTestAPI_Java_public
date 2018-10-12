package main.java.entities.contentCloud.blocks.practice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class FillTheGapsFromList extends CommonPracticeBlock {
    public String task;
    public String text;
    public Boolean mixAnswers;
    public List<Gap> gaps;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_PRACTICE + ENDPOINT_BLOCK_FILL_GAPS;

    public FillTheGapsFromList(){
        this.task = getRandomTextRandomLength(512);
        this.text = getRandomTextRandomLength(1600);
        this.gaps = new ArrayList<>();
        this.showCorrectAnswer = true;
        this.mixAnswers = true;
        this.position = 0;
        this.attempts = 2;
        this.scoring = new Scoring();
    }

    public static class Gap{
        public String right_answer;
        public ArrayList<String> options;

        public Gap(){
            this.options = new ArrayList<>();
            this.right_answer = getRandomTextRandomLength(160);
            this.options.add(getRandomTextRandomLength(160));
        }

        public Gap(String right_answer, String option){
            this.right_answer = right_answer;
            this.options.add(option);
        }
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
