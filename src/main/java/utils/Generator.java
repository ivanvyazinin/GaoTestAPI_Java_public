package main.java.utils;

import java.util.Random;

import com.github.javafaker.Faker;

public class Generator {
    public static String getRandomTextField(String field){
        long unixTime = System.currentTimeMillis();

        return field + "Auto" +unixTime;
    }

    public static int getRandomCoordinate(){
        Random rand = new Random();
        return rand.nextInt(100);
    }

    public static String getRandomText(int numberSymbols){
        Faker faker = new Faker();
        return faker.lorem().characters(numberSymbols);
    }

    public static String getTable(String cell){
        return "<table><tr><th>" +
                "Yo" +
                "</th><th>" +
                cell +
                "</th></tr></table>";
    }
}
