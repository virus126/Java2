package lesson1.animals;

public class Hen extends Animal implements Jumpable {
    private float jump_limit;

    public Hen(String name) {
        this.name = name;
        run_limit = 100;
        jump_limit = 10f;
    }

    @Override
    public boolean jump(float height) {
        return jump_limit >= height;
    }

    @Override
    public String toString() {
        return "Hen " + name;
    }
}
