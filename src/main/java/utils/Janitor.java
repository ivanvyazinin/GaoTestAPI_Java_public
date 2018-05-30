package main.java.utils;

import io.restassured.RestAssured;
import main.java.api.AuthAPI;
import main.java.steps.ContentItemSteps;
import main.java.steps.FolderSteps;
import main.java.steps.ScreenSteps;

import java.util.HashMap;
import java.util.List;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.properties.Context.HEADERS;

public class Janitor {
    public static void main(String[] args) {
        clean();
    }

    public static void clean() {
        //RestAssured.proxy("10.10.0.110", 8888);
        FolderSteps folderSteps = new FolderSteps();
        ScreenSteps screenSteps = new ScreenSteps();
        ContentItemSteps contentItemSteps = new ContentItemSteps();
        HashMap<String, String> params = new HashMap<>();
        params.put("pagination", "false");

        if(HEADERS.isEmpty()){
            AuthAPI auth = new AuthAPI();
            HEADERS.put("authorization", auth.getToken());
        }

        folderSteps.api.setRequestParameters(params);
        folderSteps.getAllFolder();
        List<HashMap> folders = folderSteps.response.jsonPath().getList("data.items");
        for (HashMap folder:folders) {
            if (folder.get("name").toString().contains("Auto") && folder.get("parentFolder").equals("00000000-0000-0000-0000-000000000001")) {
                folderSteps.deleteFolder(folder.get("id").toString());
            }
        }

        folderSteps.getFolderItems(ROOT_FOLDER);
        List<HashMap> items = folderSteps.response.jsonPath().getList("data.items");
        for (HashMap item: items){
            if (item.get("name").toString().contains("Auto")){
                Integer type =  (Integer) item.get("type");
                switch (type) {
                    case 1: folderSteps.deleteFolder(item.get("id").toString()); break;
                    case 2: screenSteps.deleteScreen(item.get("id").toString()); break;
                    case 3: contentItemSteps.deleteContentItem(item.get("id").toString()); break;
                }
            }
        }
    }
}
