package main.java.entities.directories;

import static main.java.utils.Generator.getRandomTextField;

public class Skill {
    public String name;
    public String id;

    public Skill(String name){
        this.name = name;
    }

    public Skill(){
        this.name = getRandomTextField("Skill");
    }
}
