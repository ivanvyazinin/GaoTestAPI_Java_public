package main.java.entities.directories;

import static main.java.utils.Generator.getRandomTextField;

public class Zone {
    public String name;
    public String id;

    public Zone(String name){
        this.name = name;
    }

    public Zone(){
        this.name = getRandomTextField("Zone");
    }
}
