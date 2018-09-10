package main.java.entities.contentCloud.blocks.structure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.contentCloud.blocks.AbstractBlock;

import java.util.ArrayList;
import java.util.List;

import static main.java.properties.Endpoints.*;
import static main.java.utils.Generator.getRandomTextRandomLength;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class CourseObjectives extends AbstractBlock {
    public String title;
    public List<Objective> objectives;
    public static String url = ENDPOINT_BLOCKS + ENDPOINT_BLOCKS_STRUCTURE + ENDPOINT_BLOCK_COURSE_OBJETIVES;

    public CourseObjectives(){
        this.title = getRandomTextRandomLength(159);
        this.position = 0;
        this.objectives = new ArrayList<>();
    }

    public static class Objective{
        public String point;
        public String description;

        public Objective(){
            this.point = getRandomTextRandomLength(512);
            this.description = getRandomTextRandomLength(1024);
        }

        public Objective(String point, String description){
            this.point = point;
            this.description = description;
        }
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}