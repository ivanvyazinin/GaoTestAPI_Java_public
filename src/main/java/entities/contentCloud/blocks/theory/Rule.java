package main.java.entities.contentCloud.blocks.theory;

import main.java.entities.contentCloud.blocks.AbstractBlock;

import static main.java.utils.Generator.getRandomTextField;

public class Rule extends AbstractBlock {
    public String rule;

    public Rule(String rule){
        this.rule=rule;
    }

    public Rule(){
        this.rule = getRandomTextField("Rule");

    }
}
