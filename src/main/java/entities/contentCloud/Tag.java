package main.java.entities.contentCloud;

import main.java.entities.AbstractEntity;

import static main.java.properties.Endpoints.ENDPOINT_TAGS;

public class Tag extends AbstractEntity {
    public String name;

    public Tag(String name) {
        this.name = name;url = ENDPOINT_TAGS;
    }
    @Override
    public String getUrl() {
        return this.url;
    }

}
