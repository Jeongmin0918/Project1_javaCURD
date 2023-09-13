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
        System.out.print("\n==> 수정할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("==> 수정할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine();


        System.out.print("\n==> 뜻 입력 : ");
        String meaning = s.nextLine();

        list.get(idlist.get(num-1)).setMeaning(meaning);

        System.out.println("\n단어가 수정되었습니다.\n");
    }

    @Override
    public void deleteItem() {
        System.out.print("\n==> 삭제할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("==> 삭제할 번호 선택 : ");
        int num = s.nextInt();
        s.nextLine();

        System.out.print("\n==> 정말로 삭제하시겠습니까 (Y/n) : ");
        String answer = s.nextLine();

        if(!answer.equalsIgnoreCase("y"))
            System.out.println("\n취소되었습니다.\n");
        else{
            list.remove((int)idlist.get(num-1));
            System.out.println("\n단어가 삭제되었습니다.\n");
        }
    }
    public void levelSearch() {
        System.out.print("\n==> 원하는 레벨은 : ");
        int level = s.nextInt();
        listAll(level);
    }
    @Override
    public void searchItem() {
        System.out.print("\n==> 원하는 단어는 : ");
        String keyword = s.next();
        listAll(keyword);
    }
    @Override
    public void listAll() {
        System.out.println("\n--------------------------------");
        for(int i=0; i<list.size(); i++){
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------\n");
    }
    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;

        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++){
            if(!list.get(i).getWord().contains(keyword)) continue;
            else System.out.println((j+1) + " " + list.get(i).toString());
            j++;
            idlist.add(i);
        }
        System.out.println("--------------------------------");

        return idlist;
    }
    public void listAll(int level) {
        int j = 0;

        System.out.println("--------------------------------");
        for(int i=0; i<list.size(); i++){
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            else System.out.println((j+1) + " " + list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------");

    }

}
