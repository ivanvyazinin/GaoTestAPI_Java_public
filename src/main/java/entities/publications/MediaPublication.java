package main.java.entities.publications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.core.Context;
import main.java.entities.contentCloud.Files;

import java.util.ArrayList;

import static main.java.properties.Endpoints.ENDPOINT_MEDIA_PUBLICATIONS;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class MediaPublication extends Publication {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArrayList<String> files;
    public static String url = ENDPOINT_MEDIA_PUBLICATIONS;

    public MediaPublication(){
        studies = new ArrayList<>();
        files = new ArrayList<>();
    }

    @Override
    public String getUrl() {
        return this.url;
    }


    @JsonSetter("files")
    public void setFiles(ArrayList<Files> files){
        for (Files file: files){
            this.files.add(file.id);
        }
    }

    @JsonGetter("files")
    public ArrayList<String> getFiles(){
        return files;
    }

    public MediaPublication(Context context){
        studies = new ArrayList<>();
        files = new ArrayList<>();

        name = getRandomTextField("publication name");
        description = getRandomTextField("publication description");
        language = context.getLanguage();
        studies.add(context.getStudy());
        author = getRandomTextField("author");
        license = context.getLicence();
    }

    public MediaPublication setName(String name){
        this.name = name;
        return this;
    }

    public MediaPublication setLicence(String licence){
        this.license= licence;
        return this;
    }
}
