package main.java.utils;

public class Generator {
    public static String getRandomTextField(String field){
        long unixTime = System.currentTimeMillis();

        return field + "Auto" +unixTime;
    }

}
