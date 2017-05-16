package lesson1.barriers;

import lesson1.animals.Jumpable;
import lesson1.animals.Animal;

public class Wall implements Barrier {
    private float height;

    public Wall(float height) {
        this.height = height;
    }

    public boolean doIt(Animal animal) {
        if (animal instanceof Jumpable)
            return ((Jumpable)animal).jump(height);
        else
            return false;
    }
}
