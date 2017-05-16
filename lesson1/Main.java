package lesson1;

import lesson1.animals.*;
import lesson1.barriers.*;

public class Main {
    public static void main(String[] args) {
        Course c = new Course(new Barrier[] {
                new Track(50),
                new Wall(3),
                new Water(50)
        });
        Team t1 = new Team("Team1", new Animal[] {
                new Cat("Murzik"),
                new Hen("Chika"),
                new Hippo("Lenya"),
                new Cat("Vasyan")
        });
        Team t2 = new Team("Team2", new Animal[] {
                new Cat("Fedor"),
                new Hen("Buzova"),
                new Hen("Hillary"),
                new Hippo("Motomoto")
        });
        c.doIt(t1);
        c.doIt(t2);
        t1.showResult();
        t2.showResult();
    }
}
