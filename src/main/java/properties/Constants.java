package main.java.properties;

public class Constants {
    public static final String CONTENT_TYPE = "application/json";
    public static final String ROOT_FOLDER= "00000000-0000-0000-0000-000000000001";

    //Json paths
    public static final String PATH_ID = "data.id";
    public static final String PATH_ERROR = "errors[0].code";
    public static final String PATH_COUNT = "data.count";

    //Embeds
    public static final String EMBED_TAG = "tag";
    public static final String EMBED_FOLDER = "folder";
    public static final String EMBED_FILE = "file";
    public static final String EMBED_DIRECTORY = "directory";
    public static final String EMBED_CONSTRUCTOR = "constructor";
    public static final String EMBED_BLOCS = "blocks";

    //Error codes
    public static final String ERROR_RESOURCE_ALREADY_EXISTS = "23bd9dbf-6b9b-41cd-a99e-4844bcf3077f";
    public static final String ERROR_TOO_LONG = "d94b19cc-114f-4f44-9cc4-4138e80a87b9";
    public static final String ERROR_ISCO_NOT_VALID = "9ff3fdc4-b214-49db-8718-39c315e33d45";
    public static final String ERROR_IS_BLANK = "c1051bb4-d103-4f74-8988-acbcafc7fdc3";
    public static final String ERROR_TABLE_TOO_LONG = "560847da-fc00-42f2-a8f3-9d3689802254";
    public static final String ERROR_TABLE_STRUCTURE_INVALID = "8929940b-4c61-409c-b53e-145da3c49a45";

    //Auth
    public static String STAGE_USER="admin@site.local";
    public static String STAGE_PASSWORD="123qwe";
    public static String STAGE_CLIENT_ID="00000000-0000-0000-0000-000000000000_00000000";
    public static String STAGE_CLIENT_SECRET="00000000-0000-0000-0000-000000000000";
    public static String GRANT_TYPE="password";

    //Files
    public static String FILE_PATH_IMAGE = "src\\main\\resources\\files\\image.jpg";
    public static String FILE_PATH_AUDIO = "src\\main\\resources\\files\\audio.mp3";
    public static String FILE_PATH_VIDEO = "src\\main\\resources\\files\\video.mp4";
    public static String FILE_PATH_FILE_PDF = "src\\main\\resources\\files\\file.pdf";

}
