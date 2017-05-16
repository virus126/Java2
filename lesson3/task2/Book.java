package lesson3.task2;

import java.util.*;

public class Book {
    private ArrayList<Record> book = new ArrayList<Record>();
    private ArrayList<String> searchList;

    void add(Record rec) {
        book.add(rec);
    }

    ArrayList<String> searchNumber(String surname) {
        searchList = new ArrayList<String>();
        for (int i = 0; i < book.size(); i++)
            if (book.get(i).surname.equals(surname))
                searchList.add(book.get(i).number);
        return searchList;
    }

    ArrayList<String> searchEmail(String surname) {
        searchList = new ArrayList<String>();
        for (int i = 0; i < book.size(); i++)
            if (book.get(i).surname.equals(surname))
                searchList.add(book.get(i).email);
        return searchList;
    }
}
