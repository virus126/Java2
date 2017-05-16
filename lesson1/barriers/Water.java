package lesson1.barriers;

import lesson1.animals.Swimable;
import lesson1.animals.Animal;

public class Water implements Barrier {
    private int length;

    public Water(int length) {
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        if (animal instanceof Swimable)
            return ((Swimable)animal).swim(length);
        else
            return false;
    }
}
