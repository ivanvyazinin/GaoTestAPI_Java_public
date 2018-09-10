package main.java.entities.contentCloud.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import main.java.entities.AbstractEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractBlock extends AbstractEntity {
    public int position;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int type;

    @Override
    public String getUrl() {
        return this.url;
    }


}
