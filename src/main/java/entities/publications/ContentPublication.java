package main.java.entities.publications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import main.java.core.Context;
import main.java.entities.contentCloud.folderItems.ContentItem;

import java.util.ArrayList;
import java.util.Arrays;

import static main.java.properties.Endpoints.ENDPOINT_PUBLICATIONS;
import static main.java.utils.Generator.getRandomTextField;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class ContentPublication extends Publication {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String level;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String zone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String eqf;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArrayList<String> skills;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public ArrayList<String> isco;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ArrayList<String> contentItems;

    public static String url = ENDPOINT_PUBLICATIONS;

    public ContentPublication(){
        studies = new ArrayList<>();
        skills = new ArrayList<>();
        isco = new ArrayList<>();
        contentItems = new ArrayList<>();
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

    public ContentPublication(Context context){
        studies = new ArrayList<>();
        skills = new ArrayList<>();
        isco = new ArrayList<>();
        contentItems = new ArrayList<>();

        name = getRandomTextField("publication name");
        description = getRandomTextField("publication description");
        author = getRandomTextField("author");
        license = context.getLicence();
        eqf = context.getEqf();
        level = context.getLevel();
        language = context.getLanguage();
        zone = context.getZone();
        isco.add(context.getIsco());
        skills.add(context.getSkill());
        studies.add(context.getStudy());
    }

    public ContentPublication addCi(ContentItem contentItem){
        this.contentItems.add(contentItem.id);
        return this;
    }

    public ContentPublication setName(String name){
        this.name = name;
        return this;
    }

    public ContentPublication setLevel(String level){
        this.level = level;
        return this;
    }

    public ContentPublication setStudies(String[] studies){
        this.studies.clear();
        this.studies.addAll(Arrays.asList(studies));
        return this;
    }

    public ContentPublication setLicense (String license){
        this.license = license;
        return this;
    }
}
