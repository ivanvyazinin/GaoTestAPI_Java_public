package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Rule {
    public String rule;
    public String screen;
    @JsonIgnore
    public String id;

    public Rule(String rule, String screen){
        this.rule=rule;
        this.screen=screen;
    }
}
