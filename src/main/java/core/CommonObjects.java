package main.java.core;

import main.java.entities.contentCloud.blocks.theory.Paragraph;
import main.java.entities.contentCloud.folderItems.ContentItem;
import main.java.entities.contentCloud.folderItems.Folder;
import main.java.entities.contentCloud.folderItems.Screen;
import main.java.steps.CommonSteps;
import main.java.steps.ContentItemSteps;

import static main.java.properties.Constants.ROOT_FOLDER;

public class CommonObjects {
    private CommonSteps steps = new CommonSteps();
    private Context context = Context.getInstance();

    private Folder testFolder;
    private ContentItem validCI;

    private static CommonObjects instance = null;
    public static CommonObjects getInstance(){
        if(instance==null){
            instance = new CommonObjects();
        }
        return instance;
    }

    public Folder getTestFolder() {
        if (testFolder==null){
            testFolder = steps.createEntity(new Folder(ROOT_FOLDER));
        }
        return testFolder;
    }

    public void cleanTestFolder(){
        if (testFolder!=null) {
            steps.deleteEntity(testFolder);
        }
    }

    public ContentItem getValidCI() {
        ContentItemSteps contentItemSteps = new ContentItemSteps();

        if (validCI ==null){
            validCI = contentItemSteps.getCIWithValidConstructor(
                    new ContentItem(getTestFolder().id),
                    new Screen(getTestFolder().id),
                    new Paragraph(getTestFolder().id, context.getLevel()));
        }

        return validCI;
    }



}
