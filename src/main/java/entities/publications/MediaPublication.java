package main.java.entities.publications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.contentCloud.File;

import java.util.ArrayList;

import static main.java.properties.Endpoints.ENDPOINT_MEDIA_PUBLICATIONS;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class MediaPublication extends Publication {
    public ArrayList<String> files;
    public static String url = ENDPOINT_MEDIA_PUBLICATIONS;

    public MediaPublication(){
        studies = new ArrayList();
        files = new ArrayList<>();
    }

    @Override
    public String getUrl() {
        return this.url;
    }


    @JsonSetter("files")
    public void setFiles(ArrayList<File> files){
        for (File file: files){
            this.files.add(file.id);
        }
    }

    @JsonGetter("files")
    public ArrayList<String> getFiles(){
        return files;
    }
}
