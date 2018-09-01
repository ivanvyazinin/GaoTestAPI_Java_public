package main.java.utils;

import java.util.Random;

import com.github.javafaker.Faker;

public class Generator {
    public static void main(String[] args) {
/*
        Integer a = new Integer(5);
        int b= 5;

        System.out.println(a.equals(b));
        System.out.println(a==b);
*/

        Faker faker = new Faker();
        //System.out.println(faker.lorem().words(1));
        System.out.println(faker.lorem().characters(1024));
    }

    public static String getRandomTextField(String field){
        long unixTime = System.currentTimeMillis();

        return field + "Auto" +unixTime;
    }

    public static String getRandomEmail(){
        long unixTime = System.currentTimeMillis();

        return unixTime + "@autotest.com";
    }

    public static int getRandomCoordinate(){
        Random rand = new Random();
        return rand.nextInt(100);
    }

    public static String getRandomIscoCode(){
        Random rand = new Random();
        return 0 + String.format("%03d", rand.nextInt(1000));
    }

    public static String getRandomText(int numberSymbols){
        Faker faker = new Faker();
        return faker.lorem().characters(numberSymbols);
    }

    public static String getRandomTextRandomLength(int maxNumberSymbols){
        Faker faker = new Faker();
        Random rand = new Random();
        return faker.lorem().characters(1 + rand.nextInt(maxNumberSymbols));
    }

    public static String getTable(String cell){
        return "<table><tr><td>" + cell + "</td></tr></table>";
    }
}
