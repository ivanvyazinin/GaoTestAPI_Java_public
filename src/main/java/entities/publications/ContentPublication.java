package main.java.entities.publications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.entities.contentCloud.folderItems.ContentItem;

import java.util.ArrayList;

import static main.java.properties.Endpoints.ENDPOINT_PUBLICATIONS;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class ContentPublication extends Publication {
    public String level;
    public String zone;
    public String eqf;
    public ArrayList<String> skills;
    public ArrayList<String> isco;
    public ArrayList<String> contentItems;

    public static String url = ENDPOINT_PUBLICATIONS;

    public ContentPublication(){
        studies = new ArrayList<String>();
        skills = new ArrayList<String>();
        isco = new ArrayList<String>();
        contentItems = new ArrayList<String>();
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @JsonSetter("contentItems")
    public void setContentItems(ArrayList<ContentItem> contentItems){
        for (ContentItem contentItem: contentItems){
            this.contentItems.add(contentItem.id);
        }
    }

    @JsonGetter("contentItems")
    public ArrayList<String> getContentItems(){
        return contentItems;
    }
}
