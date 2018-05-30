package main.java.steps.blocks;

import io.qameta.allure.Step;
import main.java.api.contentCloud.blocks.ParagraphAPI;
import main.java.entities.contentCloud.blocks.Paragraph;
import main.java.steps.CommonSteps;

import static main.java.properties.Constants.PATH_ID;

public class ParagraphSteps extends CommonSteps {
    public Paragraph testParagraph;

    ParagraphAPI paragraphAPI = new ParagraphAPI();

    @Step("Creating Paragraph with content '{content}' in screen '{screen}'")
    public void createParagraph(String content, String screen){
        testParagraph = new Paragraph(content, screen);
        response = paragraphAPI.post(testParagraph);
        testParagraph.id = response.jsonPath().getString(PATH_ID);
    }

    @Step("Retrieve Paragraph")
    public void getParagraph(String id){
        response = paragraphAPI.getById(id);
    }

    @Step("Delete Paragraph")
    public void deleteParagraph(String id){
        response = paragraphAPI.delete(id);
    }

    @Step("Edit Paragraph")
    public void editParagraph(String id){
        response = paragraphAPI.put(id, testParagraph);
    }

}
