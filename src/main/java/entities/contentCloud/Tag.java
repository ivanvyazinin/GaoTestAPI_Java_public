package main.java.entities.contentCloud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.entities.AbstractEntity;

import static main.java.properties.Endpoints.ENDPOINT_TAGS;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class Tag extends AbstractEntity {
    public String name;

    public Tag(String name) {
        this.name = name;url = ENDPOINT_TAGS;
    }

    public Tag() {
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
