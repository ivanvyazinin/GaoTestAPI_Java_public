package main.java.entities.publications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.AbstractEntity;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.directories.AbstractDirectory;

import java.util.ArrayList;

public abstract class Publication extends AbstractEntity {
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String language;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String license;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ArrayList<String> studies;

    @JsonSetter("language")
    public void setLanguage(AbstractDirectory lang){
            this.language= lang.id;
    }

    @JsonGetter("language")
    public String getLanguage(){
        return language;
    }

    @JsonSetter("license")
    public void setLicense(AbstractDirectory license){
            this.license= license.id;
    }

    @JsonGetter("license")
    public String getLicense(){
        return license;
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