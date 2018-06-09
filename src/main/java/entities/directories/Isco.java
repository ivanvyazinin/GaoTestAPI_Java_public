package main.java.entities.directories;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static main.java.utils.Generator.getRandomIscoCode;
import static main.java.utils.Generator.getRandomTextField;

public class Isco {
    public String name;
    public String code;

    @JsonIgnore
    public String id;

    public Isco (String name){
        this.name = name;
        this.code = getRandomIscoCode();
    }

    public Isco(String name, String code){
        this.name = name;
        this.code = code;
    }

    public Isco(){
        this.name = getRandomTextField("Isco");
        this.code = getRandomIscoCode();
    }
}
