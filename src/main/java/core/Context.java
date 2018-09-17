package main.java.core;

import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.directories.*;
import main.java.steps.CommonSteps;
import main.java.steps.DirectorySteps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.properties.Constants.ROOT_FOLDER;
import static main.java.utils.Lists.getRandomItem;

public class Context {
    public static Map<String, String> HEADERS = new HashMap<>();

    private CommonSteps steps = new CommonSteps();

    private static Context instance = null;
    public static Context getInstance(){
        if(instance==null){
            instance = new Context();
        }
        return instance;
    }

    private List<AbstractDirectory> levels;
    private List<AbstractDirectory> functionalZones;
    private List<AbstractDirectory> fieldsOfStudy;
    private List<AbstractDirectory> eqfs;
    private List<AbstractDirectory> iscos;
    private List<AbstractDirectory> licenses;
    private List<AbstractDirectory> skills;
    private List<AbstractDirectory> languages;
    private Folder testFolder;

    public String  getTestFolder() {
        if (testFolder==null){
            testFolder = steps.createEntity(new Folder(ROOT_FOLDER));
        }
        return testFolder.id;
    }

    public void cleanTestFolder(){
        if (testFolder!=null) {
            steps.deleteEntity(testFolder);
        }
    }

    public String getLevel() {
        if (levels==null){
            levels = steps.getEntites(Level.class, Level.url);
        }
        return getRandomItem(levels).id;
    }
    public String getZone() {
        if (functionalZones==null){
            functionalZones = steps.getEntites(Zone.class, Zone.url);
        }
        return getRandomItem(levels).id;
    }

    public String getEqf() {
        if (eqfs==null){
            eqfs = steps.getEntites(Eqf.class, Eqf.url);
        }
        return getRandomItem(eqfs).id;
    }

    public String getIsco() {
        if (iscos==null){
            iscos = steps.getEntites(Isco.class, Isco.url);
        }
        return getRandomItem(iscos).id;
    }

    public String getLicence() {
        if (licenses==null){
            licenses = steps.getEntites(Licence.class, Licence.url);
        }
        return getRandomItem(licenses).id;
    }

    public String getSkill() {
        if (skills==null){
            skills = steps.getEntites(Skill.class, Skill.url);
        }
        return getRandomItem(skills).id;
    }

    public String getLanguage() {
        if (languages==null){
            languages = steps.getEntites(Language.class, Language.url);
        }
        return getRandomItem(languages).id;
    }

    public String getStudy() {
        if (fieldsOfStudy==null){
            fieldsOfStudy = DirectorySteps.getDirectoryLevelTwo(Study.class, Study.url);
        }
        return getRandomItem(fieldsOfStudy).id;
    }
}
