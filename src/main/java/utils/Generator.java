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
        Long timeStamp = System.nanoTime();
        return "TA"  + field + timeStamp.toString().substring(7);
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
        String a  = faker.stock().nyseSymbol();
        if(a.length() < numberSymbols){
            return "TA " + a + " " + faker.lorem().characters(numberSymbols-a.length());
        }
        else return "TA " + a;
    }

    public static String getRandomTextRandomLength(int maxNumberSymbols){
        Faker faker = new Faker();
        String a  = faker.stock().nyseSymbol();
        if(a.length() < maxNumberSymbols){
            return "TA " + a + " " + faker.lorem().characters(1, maxNumberSymbols-a.length());
        }
        else return "TA " + a;
    }

    public static String getTable(String cell){
        return "<table><tr><td>" + cell + "</td></tr></table>";
    }
}
