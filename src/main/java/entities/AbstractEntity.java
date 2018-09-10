package main.java.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class AbstractEntity {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String id;

    @JsonIgnore
    public static String url;

    public abstract String getUrl();
}
