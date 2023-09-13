package com.org.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCURD implements ICURD{
    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";
    WordCURD(Scanner s){
       list = new ArrayList<>();
       this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("\n=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.next();
        s.nextLine();

        while(true){
            if(level >= 4 || level <= 0){
                System.out.print("\n잘못된 레벨입니다. 다시 입력해주세요!!\n" + "=> 난이도(1,2,3) : ");
                level = s.nextInt();
                s.nextLine();
            }
            else break;
        }

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
        if(idlist.isEmpty()) return;
        else{
            System.out.print("==> 수정할 번호 선택 : ");
            int num = s.nextInt();
            s.nextLine();


            System.out.print("\n==> 뜻 입력 : ");
            String meaning = s.nextLine();

            list.get(idlist.get(num-1)).setMeaning(meaning);

            System.out.println("\n단어가 수정되었습니다.\n");
        }

    }

    @Override
    public void deleteItem() {
        System.out.print("\n==> 삭제할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idlist = this.listAll(keyword);

        if(idlist.isEmpty()) return;
        else {
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
        System.out.println("\n------------------------------------------");
        for(int i=0; i<list.size(); i++){
            if(i<9) System.out.print("0" + (i+1) + " ");
            else System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("------------------------------------------\n");
    }
    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;

        System.out.println("\n------------------------------------------");
        for(int i=0; i<list.size(); i++){
            if(!list.get(i).getWord().contains(keyword)) continue;
            else if(j<9)System.out.println("0" + (j+1) + " " + list.get(i).toString());
            else System.out.println((j+1) + " " + list.get(i).toString());
            j++;
            idlist.add(i);
        }
        if(j == 0) System.out.println("검색한 단어가 존재하지 않습니다 !!!");
        System.out.println("------------------------------------------\n");

        return idlist;
    }
    public void listAll(int level) {
        int j = 0;

        System.out.println("\n------------------------------------------");
        for(int i=0; i<list.size(); i++){
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            else if(j<9) System.out.println("0" + (j+1) + " " + list.get(i).toString());
            else System.out.println((j+1) + " " + list.get(i).toString());
            j++;
        }
        if(j == 0) System.out.println("존재하지 않는 레벨입니다 !!!");
        System.out.println("------------------------------------------\n");
    }

    public void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));

            int count = 0;
            String line;

            while(true){
                //파일을 한줄씩 읽어오기
                line = br.readLine();
                if(line == null) break; //파일이 끝나면 탈출

                //|표시로 단어 나누기
                String data[] = line.split("\\|");
                int level = Integer.parseInt((data[0]));
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }

            br.close();
            System.out.println("==>" + count + "개 데이터 로딩 완료!!!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));

            for(Word one : list){
                pr.write(one.toFileString() + "\n");
            }

            pr.close();
            System.out.println("\n==> 데이터 저장 완료!!!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
