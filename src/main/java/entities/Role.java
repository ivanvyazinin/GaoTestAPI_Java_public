package main.java.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static main.java.properties.Endpoints.ENDPOINT_USER_ROLES;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role extends AbstractEntity{
    public String id;
    public String role;
    @JsonIgnore
    public static String url = ENDPOINT_USER_ROLES;

    @Override
    public String getUrl() {
        return this.url;
    }
}