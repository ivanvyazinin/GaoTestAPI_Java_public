package test.java.directories;

import main.java.steps.directories.CommonDirectorySteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.SuperTest;

import static main.java.properties.Constants.PATH_COUNT;
import static main.java.properties.Endpoints.*;

public class GetAllDirectoriesTest extends SuperTest {
    CommonDirectorySteps directorySteps;

    @BeforeClass
    public void prepare(){
        directorySteps = new CommonDirectorySteps();
    }

    @Test
    public void getLevels(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LEVEL);
        directorySteps.checkStatusCode(200);
        directorySteps.checkThatJsonContains(7, PATH_COUNT);
        }

    @Test
    public void getEQF(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_EQF);
        directorySteps.checkStatusCode(200);
        directorySteps.checkThatJsonContains(8, PATH_COUNT);
    }

    @Test
    public void getLicence(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LICENCE);
        directorySteps.checkStatusCode(200);
        directorySteps.checkThatJsonContains(10, PATH_COUNT);
    }


    @Test
    public void getLanguages(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LANGUAGE);
        directorySteps.checkStatusCode(200);
        directorySteps.checkThatJsonContains(185, PATH_COUNT);
    }

    @Test
    public void getZones(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_ZONES);
        directorySteps.checkStatusCode(200);
    }

    @Test
    public void getIsco(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_ISCO);
        directorySteps.checkStatusCode(200);
    }

    @Test
    public void getSkills(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_SKILL);
        directorySteps.checkStatusCode(200);
    }

    @Test
    public void getStudy(){
        directorySteps.getDirectory(ENDPOINT_DIRECTORY_STUDY);
        directorySteps.checkStatusCode(200);
    }
}
