package lesson1;

import lesson1.animals.Animal;

public class Team {
    private String name;
    private Animal[] members;
    boolean[] tableResult = new boolean[4];

    Team(String name, Animal[] members) {
        this.name = name;
        this.members = members;
    }

    Animal[] getMembers() {
        return members;
    }

    void showResult() {
        int i = 0;
        System.out.println(this.name);
        for(Animal animal : members)
            if (tableResult[i++])
                System.out.println(animal);
        System.out.println();
    }

    void showAll() {
        System.out.println(this.name);
        for(Animal animal : members)
            System.out.println(animal);
    }
}