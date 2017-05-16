package lesson3.task2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        book.add(new Record("Zaytsev", "3041", "azaytev@dtln.ru"));
        book.add(new Record("Petrov", "4035", "avpetrov@dtln.ru"));
        book.add(new Record("Yankin", "3061", "ayankin@dtln.ru"));
        book.add(new Record("Balkov", "3043", "abalkov@dtln.ru"));
        book.add(new Record("Efimov", "3063", "kefimov@dtln.ru"));
        book.add(new Record("Shonin", "3062", "kshonin@dtln.ru"));
        book.add(new Record("Zaytsev", "3041", "azaytev@dtln.ru"));

        ArrayList<String> search = book.searchNumber("Zaytsev");
        System.out.println(search);

        search = book.searchEmail("Zaytsev");
        System.out.println(search);
    }
}
