package lesson1.animals;

public class Hippo extends Animal implements Swimable {
    private int swim_limit;

    public Hippo(String name) {
        this.name = name;
        run_limit = 50;
        swim_limit = 200;
    }

    @Override
    public boolean swim(int length) {
        return swim_limit >= length;
    }

    @Override
    public String toString() {
        return "Hippo " + name;
    }
}