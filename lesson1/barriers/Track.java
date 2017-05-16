package lesson1.barriers;

import lesson1.animals.Animal;

public class Track implements Barrier {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        return animal.run(length);
    }
}
