import java.util.Scanner;
import java.util.Stack;

public class zadanie3 {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        String str = in.nextLine();

        String[] strings = str.split(" ");

        Stack<Double> stack = new Stack<Double>();

        for (String string : strings) {
            if (isNumber(string)) {
                stack.push(Double.parseDouble(string));
            } else {
                double tmp1 = stack.pop();
                double tmp2 = stack.pop();

                switch (string) {
                    case "+" -> stack.push(tmp1 + tmp2);
                    case "-" -> stack.push(tmp2 - tmp1);
                    case "*" -> stack.push(tmp1 * tmp2);
                    case "/" -> stack.push(tmp2 / tmp1);
                }
            }
        }
        if (!stack.empty()) {
            System.out.println(stack.pop());
        } else {
            System.out.println("Ошибка");
        }
    }

    private  static  boolean isNumber(String string) {
        try {
            Double.parseDouble(string);

            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}