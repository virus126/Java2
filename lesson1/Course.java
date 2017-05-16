package lesson1;

import lesson1.animals.*;
import lesson1.barriers.Barrier;

public class Course {
    Barrier[] barriers;

    Course (Barrier[] barriers) {
        this.barriers = barriers;
    }

    void doIt(Team team) {
        int i = 0;
        for (Animal animal: team.getMembers()) {
            boolean flag = true;
            for (Barrier barrier : barriers)
                if (!barrier.doIt(animal)) {
                    flag = false;
                    break;
                }
            team.tableResult[i++] = flag;
        }
    }
}
