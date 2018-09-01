package main.java.steps.blocks;

import main.java.api.contentCloud.blocks.CommonBlocsAPI;
import main.java.entities.contentCloud.blocks.ReusableBlock;
import static main.java.utils.Generator.getRandomText;

public class BlockSteps {
    CommonBlocsAPI blockBlocsAPI;

    public <T extends ReusableBlock> T getReusableBlock(T block)  {
        blockBlocsAPI = new CommonBlocsAPI(block.url);
        block.reusable = true;
        block.name = getRandomText(11);
        block.id = blockBlocsAPI.post(block).jsonPath().getObject("data",block.getClass()).id;
        return block;
    }
}
