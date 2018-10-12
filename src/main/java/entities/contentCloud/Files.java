package main.java.entities.contentCloud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.AbstractEntity;

import static main.java.properties.Endpoints.ENDPOINT_FILES;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Files extends AbstractEntity {
    public static String url = ENDPOINT_FILES;
    public String fileLicense;

    public Files(){

    }

    public Files(String fileLicense){
        this.fileLicense = fileLicense;
    }

    public String getUrl(){
        return this.url;
    }
}
