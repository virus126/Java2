package lesson3.task1;

public class Main {
    public static void main(String[] args) {
        String[] array =
                ("London is the capital of the United Kingdom" +
                        " Together with Tokyo and New York is one of the three largest cities in the world" +
                        " London is situated on the river Thames" +
                        " It divides the city into north and south" +
                        " The Londoners call the Thames simply the river" +
                        " There are fifteen bridges across the river").split(" ");

        Task1.toLower(array);
        System.out.println("Words occur in the text next number of times:");
        System.out.println(Task1.getMap(array));
        System.out.println();
        System.out.println("The text contains the following words:");
        System.out.println(Task1.getSet(array));

    }
}
