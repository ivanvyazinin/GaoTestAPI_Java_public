package main.java.entities.contentCloud.blocks.practice;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.contentCloud.Files;

import java.util.ArrayList;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoinSentencePicture extends CommonPracticeBlock {
    public String task;
    private ArrayList<String> files;
    public ArrayList<Sentence> sentences;

    @JsonIgnore
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_PRACTICE + ENDPOINT_BLOCK_JOIN_SENTENCE_PICTURE;

    public JoinSentencePicture(){
    }

    public JoinSentencePicture(String name){
        task = name;
        sentences = new ArrayList<>();

        this.files = new ArrayList<>();

        this.showCorrectAnswer = true;
        this.position = 0;
        this.attempts = 2;
        this.scoring = new Scoring();

        type = 22;
    }

    public void addItem(String file){
        files.add(file);
        sentences.add(new Sentence(file));
    }

    @JsonSetter("files")
    public void setFiles(ArrayList<Files> block_files){
        files = new ArrayList<>();
        for (Files file:block_files){
            this.files.add(file.id);
        }
    }

    @JsonGetter("files")
    public ArrayList<String> getFiles(){
        return files;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sentence{
        public String file_id;
        public ArrayList<String> items;

        public Sentence(){

        }

        public Sentence(String file){
            file_id = file;
            items = new ArrayList<>();

            //TODO add method to generate random array of strings
            items.add(getRandomTextRandomLength(1024));
            items.add(getRandomTextRandomLength(1024));
            items.add(getRandomTextRandomLength(1024));
        }
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
