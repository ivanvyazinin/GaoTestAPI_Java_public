package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractBlock {
    public int position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String id;
    public int type;

}
