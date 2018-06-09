package test.java.publications;

import main.java.entities.directories.AbstractDirectory;
import main.java.entities.directories.AbstractDirectoryResponse;
import main.java.steps.directories.CommonDirectorySteps;
import org.testng.annotations.BeforeSuite;
import test.java.SuperTest;

import java.util.ArrayList;

import static main.java.properties.Endpoints.*;
import static main.java.properties.Endpoints.ENDPOINT_DIRECTORY_LANGUAGE;

public class SuperPublicationTest extends SuperTest {
    ArrayList<AbstractDirectory> level;
    ArrayList<AbstractDirectory> functionalZone;
    ArrayList<AbstractDirectory> fieldsOfStudy;
    ArrayList<AbstractDirectory> eqf;
    ArrayList<AbstractDirectory> isco;
    ArrayList<AbstractDirectory> license;
    ArrayList<AbstractDirectory> skill;
    ArrayList<AbstractDirectory> language;

    @BeforeSuite
    public void initializeDirectories(){
        CommonDirectorySteps directorySteps = new CommonDirectorySteps();

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_LEVEL);
        level = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_ZONES);
        functionalZone = directorySteps.response.as(AbstractDirectoryResponse.class).data.items;

        directorySteps.getDirectory(ENDPOINT_DIRECTORY_STUDY);
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
