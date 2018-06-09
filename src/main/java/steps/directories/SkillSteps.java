package main.java.steps.directories;

import io.qameta.allure.Step;
import main.java.api.directories.SkillAPI;
import main.java.entities.directories.Skill;

import static main.java.properties.Constants.PATH_ID;

public class SkillSteps extends CommonDirectorySteps {
    public Skill testSkill;
    public SkillAPI skillsAPI = new SkillAPI();

    @Step("Creating skill with name '{name}'")
    public void createSkill(String name){
        testSkill = new Skill(name);
        response = skillsAPI.post(testSkill);
        testSkill.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Creating skill with random name")
    public void createSkill(){
        testSkill = new Skill();
        response = skillsAPI.post(testSkill);
        testSkill.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Edit skill")
    public void editSkill(String skillId){
        response=skillsAPI.put(skillId,testSkill);
    }

    @Step("Delete skill")
    public void deleteSkill(String skillId){
        response=skillsAPI.delete(skillId);
    }

    @Step("Get skill")
    public void getSkill(String skillId){
        response=skillsAPI.getById(skillId);
    }

}
