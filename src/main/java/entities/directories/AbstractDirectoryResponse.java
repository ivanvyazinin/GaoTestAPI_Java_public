package main.java.entities.directories;

import java.util.ArrayList;

public class AbstractDirectoryResponse {
    public Data data;

    public class Data{
        public int count;
        public ArrayList <AbstractDirectory> items;
    }
}
