package main.java.entities.publications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.AbstractEntity;
import main.java.entities.directories.AbstractDirectory;

import java.util.ArrayList;

public abstract class Publication extends AbstractEntity {
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String language;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArrayList<String> studies;
    public String license;
    public String author;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String sourceLink;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String licenseNotes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer status;

    @JsonSetter("language")
    public void setLanguage(AbstractDirectory lang){
            this.language= lang.id;
    }

    @JsonGetter("language")
    public String getLanguage(){
        return language;
    }

    @JsonSetter("studies")
    public void setStudies(ArrayList<AbstractDirectory> studies){
        for (AbstractDirectory study: studies){
            this.studies.add(study.id);
        }
    }

    @JsonGetter("studies")
    public ArrayList<String> getStudies(){
        return studies;
    }
}