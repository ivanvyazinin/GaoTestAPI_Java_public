package test.java.publications;

import main.java.entities.directories.AbstractDirectory;
import main.java.entities.directories.AbstractDirectoryResponse;
import main.java.steps.directories.CommonDirectorySteps;
import org.testng.annotations.BeforeSuite;
import test.java.SuperTest;

import java.util.ArrayList;

import static main.java.properties.Endpoints.*;

public class SuperPublicationTest extends SuperTest {
    static ArrayList<AbstractDirectory> level;
    static ArrayList<AbstractDirectory> functionalZone;
    static ArrayList<AbstractDirectory> fieldsOfStudy;
    static ArrayList<AbstractDirectory> eqf;
    static ArrayList<AbstractDirectory> isco;
    static ArrayList<AbstractDirectory> license;
    static ArrayList<AbstractDirectory> skill;
    static ArrayList<AbstractDirectory> language;

    @BeforeSuite
    public void initializeDirectories(){
        CommonDirectorySteps directorySteps = new CommonDirectorySteps();

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LEVEL);
        level = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_ZONES);
        functionalZone = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectoryLevelTwo(ENDPOINT_DIRECTORY_STUDY);
        fieldsOfStudy = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_EQF);
        eqf = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_ISCO);
        isco = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LICENCE);
        license = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_SKILL);
        skill  = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LANGUAGE);
        language  = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;
    }

}
