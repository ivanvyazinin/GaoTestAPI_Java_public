package main.java.utils;

import java.util.Random;

import com.github.javafaker.Faker;

public class Generator {
    public static void main(String[] args) {

        Faker faker = new Faker();
        //System.out.println(faker.lorem().words(1));
        //System.out.println(faker.lorem().characters(48));
        System.out.println(getRandomTextField("aa"));
    }

    public static String getRandomTextField(String field){
        Faker faker = new Faker();
        String a  = faker.stock().nyseSymbol().replace(" ", "");
        Long timeStamp = System.nanoTime();
        return field + a + timeStamp.toString().substring(7) + "TA";
    }

    public static String getRandomEmail(String mark){
        long unixTime = System.currentTimeMillis();

        return mark + unixTime + "@autotest.com";
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
        String a  = faker.stock().nyseSymbol().replace(" ", "");
        if(a.length() < numberSymbols){
            return a + " " + faker.lorem().characters(numberSymbols-a.length()-3) + "TA";
        }
        else return a + "TA ";
    }

    public static String getRandomTextRandomLength(int maxNumberSymbols){
        Faker faker = new Faker();
        String a  = faker.stock().nyseSymbol().replace(" ", "");
        if(a.length() < maxNumberSymbols){
            return a + " " + faker.lorem().characters(1, maxNumberSymbols-a.length()-3) + "TA";
        }
        else return a + "TA";
    }

    public static String getTable(String cell){
        return "<table><tr><td>" + cell + "</td></tr></table>";
    }
}
