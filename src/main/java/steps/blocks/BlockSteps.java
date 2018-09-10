package main.java.steps.blocks;

import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.ReusableBlock;
import main.java.entities.contentCloud.blocks.practice.JoinSentencePicture;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.steps.CommonSteps;
import main.java.steps.FilesSteps;

import static main.java.properties.Context.FILE_PATH_IMAGE;
import static main.java.utils.Generator.getRandomText;
import static main.java.utils.Generator.getRandomTextRandomLength;

public class BlockSteps extends CommonSteps {
    public CommonBlocsAPI blockBlocsAPI;

    public <T extends ReusableBlock> T getReusableBlock(T block) {
        blockBlocsAPI = new CommonBlocsAPI(block.getUrl());
        block.reusable = true;
        block.name = getRandomText(11);

        block.id = blockBlocsAPI.post(block).jsonPath().getObject("data", block.getClass()).id;
        return block;
    }

    public JoinSentencePicture getJoinSentencePictureBlock(Folder folder, String level){
        FilesSteps filesSteps = new FilesSteps();
        JoinSentencePicture testBlock = new JoinSentencePicture(getRandomTextRandomLength(512));
        testBlock.addItem(filesSteps.uploadFile(FILE_PATH_IMAGE));
        testBlock.addItem(filesSteps.uploadFile(FILE_PATH_IMAGE));
        testBlock.setParentFolder(folder);
        testBlock.level = level;
        getReusableBlock(testBlock);
        return testBlock;
    }
}
