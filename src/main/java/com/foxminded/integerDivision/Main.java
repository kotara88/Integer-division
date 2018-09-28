package com.foxminded.integerDivision;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        Main mainClass = new Main();

        while (flag) {
            int dividend = mainClass.inputNumber("dividend");
            int divisor = mainClass.inputNumber("divisor");

            if (divisor == 0) {
                System.out.println("Divisor can't be zero!!");
                continue;
            }
            if (Math.abs(dividend) < divisor){
                System.out.println(dividend + "/" + divisor + " = " + dividend / divisor);
                break;
            }

            Divider divider = new Divider();
            String result = divider.doDivision(dividend, divisor);
            System.out.println(result);
        }
    }

    private int inputNumber(String numberName) {
        System.out.println("Input a " + numberName);
        Scanner scanner = new Scanner(System.in);
        String numberStr = scanner.nextLine();

        if (numberStr.equals("q"))
            System.exit(0);

        int dividend;
        try {
            dividend = Integer.parseInt(numberStr);
            return dividend;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format! Input " + numberName + " again.");
            dividend = inputNumber(numberName);
        }
        return dividend;
    }

}
