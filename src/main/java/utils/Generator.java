package main.java.utils;

import java.util.Random;

public class Generator {
    public static String getRandomTextField(String field){
        long unixTime = System.currentTimeMillis();

        return field + "Auto" +unixTime;
    }

    public static int getRandomCoordinate(){
        Random rand = new Random();
        return rand.nextInt(100);
    }

}
