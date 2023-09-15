package com.org.word;

import java.util.ArrayList;

public interface ICURD {
    public Object add();
    public void addItem();
    public void updateItem();
    public void deleteItem();
    public void levelSearch();
    public void searchItem();
    public void listAll();
    public ArrayList<Integer> listAll(String keyword);
    public void listAll(int level);
    public void loadFile();
    public void saveFile();
}
