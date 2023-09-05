package com.org.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCURD implements ICURD{
    ArrayList<Word> list;
    Scanner s;
    WordCURD(Scanner s){
       list = new ArrayList<>();
       this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    @Override
    public void addItem() {
        Word one = (Word)add();
        list.add(one);

        System.out.println("\n새 단어가 단어장에 추가되었습니다 !!!\n");
    }

    @Override
    public void updateItem() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void listAll() {
        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++){
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }
}
