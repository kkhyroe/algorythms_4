import javax.swing.*;
import java.util.Stack;
import java.awt.*;

public class zadanie3 {
    public static void main(String[] args) {

        JFrame j = new JFrame("Calculator");

        Container c = j.getContentPane();

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        p1.setLayout(new GridLayout(4, 5, 4, 4));
        final JTextField t = new JTextField(100);
        Font myFontSize = t.getFont().deriveFont(Font.BOLD, 50f);
        t.setFont(myFontSize);
        c.add(t, BorderLayout.NORTH);

        final JButton n1 = new JButton("1");
        n1.addActionListener(e -> {
            String num1 = n1.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n2 = new JButton("2");
        n2.addActionListener(e -> {
            String num1 = n2.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n3 = new JButton("3");
        n3.addActionListener(e -> {
            String num1 = n3.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n4 = new JButton("4");
        n4.addActionListener(e -> {
            String num1 = n4.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n5 = new JButton("5");
        n5.addActionListener(e -> {
            String num1 = n5.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n6 = new JButton("6");
        n6.addActionListener(e -> {
            String num1 = n6.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n7 = new JButton("7");
        n7.addActionListener(e -> {
            String num1 = n7.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n8 = new JButton("8");
        n8.addActionListener(e -> {
            String num1 = n8.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n9 = new JButton("9");
        n9.addActionListener(e -> {
            String num1 = n9.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n10 = new JButton("0");
        n10.addActionListener(e -> {
            String num1 = n10.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n11 = new JButton("(");
        n11.addActionListener(e -> {
            String num1 = n11.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n12 = new JButton(")");
        n12.addActionListener(e -> {
            String num1 = n12.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n13 = new JButton("+");
        n13.addActionListener(e -> {
            String num1 = n13.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n14 = new JButton("-");
        n14.addActionListener(e -> {
            String num1 = n14.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n15 = new JButton("*");
        n15.addActionListener(e -> {
            String num1 = n15.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n16 = new JButton("/");
        n16.addActionListener(e -> {
            String num1 = n16.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n17 = new JButton("^");
        n17.addActionListener(e -> {
            String num1 = n17.getText();
            String global = t.getText();
            global = global.concat(num1);
            t.setText(global);
        });

        final JButton n18 = new JButton("=");
        n18.addActionListener(e -> {
            String global = t.getText();
            Solution sol = new Solution();
            global = String.valueOf(sol.calculate(global));
            if (global.equals("-1")) {
                t.setText("Ошибка (текст)");
            } else {
                t.setText(global);
            }
        });

        final JButton n19 = new JButton("C");
        n19.addActionListener(e -> t.setText(null));

        p1.add(n1);
        p1.add(n2);
        p1.add(n3);
        p1.add(n4);
        p1.add(n5);
        p1.add(n6);
        p1.add(n7);
        p1.add(n8);
        p1.add(n9);
        p1.add(n10);
        p1.add(n11);
        p1.add(n12);
        p1.add(n13);
        p1.add(n14);
        p1.add(n15);
        p1.add(n16);
        p1.add(n17);
        p1.add(n18);
        p1.add(n19);

        c.add(p1, BorderLayout.CENTER);

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(400, 400);
        j.setVisible(true);
    }
}

class Solution {
    char[] arr;
    int index = 0;

    public int calculate(String s) {
        arr = s.toCharArray();
        return dfs();
    }

    private int dfs() {
        Stack<Integer> stack = new Stack<>();
        char operator = '+';

        while (index < arr.length) {
            if (arr[index] != ' ') {
                if (Character.isDigit(arr[index])) {
                    StringBuilder buildNum = new StringBuilder();
                    while (index < arr.length && Character.isDigit(arr[index])) {
                        buildNum.append(arr[index++]);
                    }
                    index--;

                    int curNum = Integer.parseInt(buildNum.toString());
                    insertElement(stack, curNum, operator);
                } else if (arr[index] == '(') {
                    index++;
                    int curNum = dfs();
                    insertElement(stack, curNum, operator);
                } else if (arr[index] == ')') {
                    break;
                } else if (Character.isAlphabetic(arr[index])) {
                    return -1;
                } else {
                    operator = arr[index];
                }
            }
            index++;
        }

        int total = 0;
        while (!stack.isEmpty()) {
            total += stack.pop();
        }
        return total;
    }

    private void insertElement(Stack<Integer> stack, int curNum, char operator) {
        if (operator == '-') {
            curNum *= -1;
        } else if (operator == '*') {
            curNum *= stack.pop();
        } else if (operator == '/') {
            curNum = stack.pop() / curNum;
        } else if (operator == '^') {
            curNum = (int) Math.pow(stack.pop(), curNum);
        }
        stack.push(curNum);
    }
}