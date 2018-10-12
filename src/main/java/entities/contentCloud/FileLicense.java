package main.java.entities.contentCloud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.java.core.Context;
import main.java.entities.AbstractEntity;

import static main.java.properties.Endpoints.ENDPOINT_FILE_LICENSES;
import static main.java.utils.Generator.getRandomText;

@JsonIgnoreProperties(ignoreUnknown = true, value={ "url"}, allowSetters= true)
public class FileLicense extends AbstractEntity {
    public String license;
    public String author;
    public String sourceLink;
    public String licenseNotes;

    public static String url = ENDPOINT_FILE_LICENSES;

    @Override
    public String getUrl() {
        return this.url;
    }

    public FileLicense(){}

    public FileLicense(Context context){
        license = context.getLicence();
        author = getRandomText(11);
        licenseNotes = getRandomText(11);
        sourceLink = "https://TA.test.com/";
    }
}
