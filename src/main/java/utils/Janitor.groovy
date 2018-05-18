package main.java.utils

import io.restassured.RestAssured
import main.java.api.AuthAPI
import test.java.steps.ContentItemSteps
import test.java.steps.FolderSteps
import test.java.steps.ScreenSteps

import static main.java.properties.Constants.ROOT_FOLDER
import static main.java.properties.Context.AUTH_TOKEN
import static main.java.properties.Context.HEADERS

public class Janitor {
    public static void main(String[] args) {
        clean()
    }

    public static void clean(){
        RestAssured.proxy("10.10.0.110",8888);

        if (AUTH_TOKEN.equals("")){
            AuthAPI auth = new AuthAPI();
            AUTH_TOKEN = auth.getToken();
            HEADERS.put("authorization", AUTH_TOKEN );
        }

        FolderSteps folderSteps = new FolderSteps()
        ScreenSteps screenSteps = new ScreenSteps ()
        ContentItemSteps contentItemSteps = new ContentItemSteps()

        folderSteps.api.setRequestParameters(
                [
                        pagination   : "false",
                ]
        )

        folderSteps.getAllFolder()
        List folders = folderSteps.response.jsonPath().getList("data.items")
        folders.each {
            if (it.asType(Map).get("name").toString().contains("Auto")){
                folderSteps.deleteFolder(it.asType(Map).get("id").toString())
            }
        }

        folderSteps.api.setRequestParameters(
                [
                        per_page   : "200",
                ]
        )

        folderSteps.getFolderItems(ROOT_FOLDER);
        List items = folderSteps.response.jsonPath().getList("data.items")
        items.each {
            if (it.asType(Map).get("name").toString().contains("Auto")){
                switch (it.asType(Map).get("type")){
                    case 1: folderSteps.deleteFolder(it.asType(Map).get("id").toString()); break
                    case 2: screenSteps.deleteScreen(it.asType(Map).get("id").toString()); break
                    case 3: contentItemSteps.deleteContentItem(it.asType(Map).get("id").toString()); break
                }
            }
        }
    }
}
