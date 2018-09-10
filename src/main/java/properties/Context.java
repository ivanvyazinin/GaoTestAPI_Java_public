package main.java.properties;

import java.util.HashMap;
import java.util.Map;

public class Context {

    //Stage
    public static String STAGE_USER="admin@site.local";
    public static String STAGE_PASSWORD="123qwe";
    public static String STAGE_CLIENT_ID="00000000-0000-0000-0000-000000000000_00000000";
    public static String STAGE_CLIENT_SECRET="00000000-0000-0000-0000-000000000000";
    public static String GRANT_TYPE="password";

    public static Map<String, String> HEADERS = new HashMap<>();

    public static String FOLDER_FOR_TESTS = "";

    public static String FILE_PATH_IMAGE = "src\\main\\resources\\files\\image.jpg";
    public static String FILE_PATH_AUDIO = "src\\main\\resources\\files\\audio.mp3";
    public static String FILE_PATH_VIDEO = "src\\main\\resources\\files\\video.mp4";
    public static String FILE_PATH_FILE_PDF = "src\\main\\resources\\files\\file.pdf";
}
