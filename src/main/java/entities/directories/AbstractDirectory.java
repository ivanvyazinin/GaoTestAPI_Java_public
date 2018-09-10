package main.java.entities.directories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.AbstractEntity;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class AbstractDirectory extends AbstractEntity {
    public Object parent;
    public String path;
    public String name;
    public String directoryEntity;
    public int structure;
    public int type;
    public int level;
    public int position;

    //isco
    public String code;

    //licence
    public String description;

    public String getUrl(){
        return this.url;
    }
}
