package test.java.directories;

import main.java.steps.directories.CommonDirectorySteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Endpoints.*;

public class SearchDirectoriesTest extends SuperTest {
    CommonDirectorySteps directorySteps;

    @BeforeClass
    public void prepare(){
        directorySteps = new CommonDirectorySteps();
    }

    @Test
    public void searchEQF(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_EQF, "EQF1");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
    }

    @Test
    public void partialSearchEQF(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_EQF, "EQF");
        directorySteps.checkStatusCode(200);
        directorySteps.checkThatBodyHasValue("EQF1");
    }

    @Test
    public void searchLevel(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_LEVEL, "A");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
    }

    @Test
    public void NothingFoundSearchLevel(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_LEVEL, "ASD");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(0);
    }

    @Test
    public void searchLicence(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_LICENCE, "Public Domain");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchLicenceByDescription(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_LICENCE, "Public Domain Items");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(0);
    }

    @Test
    public void searchLanguage(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_LANGUAGE, "Russian");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchSkill(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_SKILL, "Leadership");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchFunctionalZones(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_ZONES, "Managing");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
    }

    @Test
    public void searchIscoName(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_ISCO, "Government social benefits official");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
        directorySteps.checkThatJsonContains("3443","data.items[0].code");
    }

    @Test
    public void searchIscoCode(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_ISCO, "3443");
        directorySteps.checkStatusCode(200);
        directorySteps.checkItemsNumberInResponse(1);
        directorySteps.checkThatJsonContains("Government social benefits official","data.items[0].name");
    }

    @Test
    public void searchStudy(){
        directorySteps.searchIntoDirectory(ENDPOINT_DIRECTORY_STUDY, "history");
        directorySteps.checkStatusCode(200);
        directorySteps.checkThatBodyHasValue("\"level\":2");
        directorySteps.checkThatBodyHasValue("\"level\":1");
    }
}
