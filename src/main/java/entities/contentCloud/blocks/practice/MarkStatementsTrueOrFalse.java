package main.java.entities.contentCloud.blocks.practice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class MarkStatementsTrueOrFalse extends CommonPracticeBlock {
    public String task;
    public List<Statement> statements;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_PRACTICE + ENDPOINT_BLOCK_MARK_STATEMENTS;

    public MarkStatementsTrueOrFalse(){
        this.task = getRandomTextRandomLength(512);
        this.showCorrectAnswer = true;
        this.position = 0;
        this.attempts = 2;
        this.statements = new ArrayList<>();
        this.scoring = new Scoring();
    }

    public static class Statement{
        public String statement;
        public boolean correct;

        public Statement(){
            this.statement = getRandomTextRandomLength(1024);
            this.correct = true;
        }

        public Statement(String answer, boolean correct){
            this.statement = answer;
            this.correct = correct;
        }
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
