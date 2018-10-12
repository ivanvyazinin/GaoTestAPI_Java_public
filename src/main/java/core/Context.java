package main.java.core;

import main.java.entities.Role;
import main.java.entities.contentCloud.FileLicense;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.directories.*;
import main.java.steps.CommonSteps;
import main.java.steps.DirectorySteps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.db.DuoyulongDao.cleanTestPublications;
import static main.java.db.DuoyulongDao.cleanTestUsers;
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
    private List<Role> roles;
    private Folder testFolder;
    private FileLicense fileLicense;

    public String getTestFolder() {
        CommonSteps steps = new CommonSteps();
        if (testFolder==null){
            testFolder = steps.createEntity(new Folder(ROOT_FOLDER));
        }
        return testFolder.id;
    }

    public void clean(){
        if (testFolder!=null) {
            steps.deleteEntity(testFolder);
        }
        cleanTestPublications();
        cleanTestUsers();
    }

    public String getRole(){
        if (roles==null){
            roles = steps.getEntites(Role.class, Role.url);
        }
        return getRandomItem(roles).id;
    }

    public String getRoleByName(String roleName){
        getRole();

        for (Role role:roles){
            if(role.role.equals(roleName)) return role.id;
        }
        return null;
    }

    public String getLevel() {
        CommonSteps steps = new CommonSteps();
        if (levels==null){
            levels = steps.getEntites(Level.class, Level.url);
        }
        return getRandomItem(levels).id;
    }

    public String getZone() {
        if (functionalZones==null){
            functionalZones = steps.getEntites(Zone.class, Zone.url);
        }
        return getRandomItem(functionalZones).id;
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

    public String getLicence(int index) {
        if (licenses==null){
            licenses = steps.getEntites(Licence.class, Licence.url);
        }
        return licenses.get(index).id;
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

    public FileLicense getFileLicense(){
        if (fileLicense == null){
            fileLicense = steps.createEntity(
                    new FileLicense(this)
            );
        }
        return fileLicense;
    }
}
