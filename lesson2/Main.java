//1. Есть строка вида: "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0"; (другими словами матрица 4x4)
//      1 3 1 2
//      2 3 2 2
//      5 6 7 1
//      3 3 1 0
//  Написать метод, на вход которого подаётся такая строка, метод должен преобразовать строку в двумерный массив типа String[][];
//
//2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;
//
//3. Ваш метод должен бросить исключения в случаях:
//    1. Если размер матрицы, полученной из строки, не равен 4x4;
//    2. Если в одной из ячеек полученной матрицы не число; (например символ или слово)
//
//4. В методе main необходимо вызвать полученный метод, обработать возможные исключения и вывести результат расчета.

package lesson2;

public class Main {
    static String[][] task1(String str) throws Exception {
        int i = 0, j = 0;
        String[][] array = new String[4][4];
        String[] split1 = str.split("\n");
        for(String s1 : split1) {
            String[] split2 = s1.split(" ");
            for(String s2 : split2) {
                array[i][j] = s2;
                j++;
            }
            i++;
            j = 0;
        }
        return array;
    }

    static double task2(String[][] array) throws Exception {
        double result = 0;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length; j++)
                result += Integer.valueOf(array[i][j]);
        return result / 2;
    }

    public static void main(String[] args) {
        try {
            System.out.println(task2(task1("1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0")));
        }
//      Обработка ошибок первого типа
        catch (IndexOutOfBoundsException e) {
            System.out.println(e.getClass().getName());
        }
//      Обработка ошибок второго типа
        catch (NumberFormatException e) {
            System.out.println(e.getClass().getName());
        }
//      Обработка всех остальных ошибок
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
