package lesson1.animals;

public class Cat extends Animal implements Jumpable, Swimable {
    private int swim_limit;
    private float jump_limit;

    public Cat(String name) {
        this.name = name;
        run_limit = 100;
        swim_limit = 100;
        jump_limit = 3.8f;
    }

    @Override
    public boolean swim(int length) {
        return swim_limit >= length;
    }

    @Override
    public boolean jump(float height) {
        return jump_limit >= height;
    }

    @Override
    public String toString() {
        return "Cat " + name;
    }
}
