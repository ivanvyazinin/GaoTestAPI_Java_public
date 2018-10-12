package main.java.utils;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;

import java.util.List;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.core.Context.HEADERS;

public class Janitor {
    public static void main(String[] args) {
        clean();
    }

    public static void clean() {
        RestAssured.proxy("10.10.0.111", 8888);
        CommonSteps steps = new CommonSteps();


        AuthAPI auth = new AuthAPI();
        HEADERS.put("authorization", "Bearer " + auth.getToken());

        steps.api.setRequestParameters(new String[][] {
                {"pagination","false"},
                {"parentFolder",ROOT_FOLDER}
        });
        List<Folder> folders = steps.getEntites(Folder.class,Folder.url);
        for (Folder folder:folders){
            if (folder.name.contains("TA") && folder.parentFolder.equals(ROOT_FOLDER)) { steps.deleteEntity(folder); }
        }

        steps.api.setRequestParameters(new String[][] {
                {"pagination","false"}
        });

        List<ContentItem> contentItems = steps.getEntites(ContentItem.class, ContentItem.url);
        for (ContentItem item:contentItems){
            if (item.name.contains("TA")) { steps.deleteEntity(item); }
        }

        List<Screen> screns = steps.getEntites(Screen.class, Screen.url);
        for (Screen item:screns){
            if (item.name.contains("TA")) { steps.deleteEntity(item); }
        }

        folders = steps.getEntites(Folder.class,Folder.url);
        for (Folder folder:folders){
            if (folder.name.contains("TA") && folder.parentFolder.equals(ROOT_FOLDER)) { steps.deleteEntity(folder); }
        }

    }
}
