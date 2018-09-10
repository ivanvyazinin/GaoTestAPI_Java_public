package main.java.utils;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.FolderItem;
import main.java.steps.CommonSteps;

import java.util.List;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.HEADERS;

public class Janitor {
    public static void main(String[] args) {
        clean();
    }

    public static void clean() {
        RestAssured.proxy("10.10.0.113", 8888);
        CommonSteps steps = new CommonSteps();

        String [][] parameters = {
                {"pagination","false"},
        };
        steps.api.setRequestParameters(parameters);

        AuthAPI auth = new AuthAPI();
        HEADERS.put("authorization", "Bearer " + auth.getToken());

        List<Folder> folders = steps.getEntites(Folder.class,Folder.url);
        for (Folder folder:folders){
            if (folder.name.contains("Auto") && folder.parentFolder.equals(ROOT_FOLDER)) { steps.deleteEntity(folder); }
        }

        List<FolderItem> folderItems = steps.getEntites(FolderItem.class, FolderItem.url);
        for (FolderItem item:folderItems){
            if (item.name.contains("Auto")) { steps.deleteEntity(item); }
        }
    }
}
