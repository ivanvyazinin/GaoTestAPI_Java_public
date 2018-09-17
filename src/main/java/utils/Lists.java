package main.java.utils;

import java.util.List;
import java.util.Random;

public class Lists {
    public static  <T> T getRandomItem(List<T> list)
    {
        Random random = new Random();
        int listSize = list.size();
        int randomIndex = random.nextInt(listSize);
        return list.get(randomIndex);
    }
}
