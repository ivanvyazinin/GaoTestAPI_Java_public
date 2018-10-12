package main.java.steps.blocks;

import main.java.api.contentCloud.GroupOfBlocksAPI;
import main.java.core.Context;
import main.java.entities.contentCloud.blocks.practice.JoinSentencePicture;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;

import java.util.List;

import static main.java.properties.Constants.FILE_PATH_IMAGE;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

public class BlockSteps extends CommonSteps {
    public CommonSteps steps;

    public JoinSentencePicture getJoinSentencePictureBlock(String folderId, Context context){
        FilesSteps filesSteps = new FilesSteps();
        steps = new CommonSteps();
        JoinSentencePicture testBlock = new JoinSentencePicture(getRandomTextRandomLength(512));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.addItem(filesSteps.uploadFileWithLicense(FILE_PATH_IMAGE, context));
        testBlock.setFolder(folderId);
        testBlock.level = context.getLevel();
        testBlock.reusable = true;
        testBlock.name = getRandomText(11);
        testBlock = steps.createEntity(testBlock);
        return testBlock;
    }

    public List<String> getScreensOfGroupBlocks(String groupOfBlocksId){
        GroupOfBlocksAPI groupOfBlocksAPI = new GroupOfBlocksAPI();
        response = groupOfBlocksAPI.getScreens(groupOfBlocksId);
        return response.jsonPath().getList("data.items", String.class);
    }
}
