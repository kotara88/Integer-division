package com.foxminded.integerDivision;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("If you want to exit input \"q\" for dividend");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            int dividend;
            System.out.println("Input a dividend");
            String dividendStr = scanner.nextLine();

            if (dividendStr.equals("q"))
                break;

            try {
                dividend = Integer.parseInt(dividendStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format! Input dividend again.");
                continue;
            }


            int divisor;
            System.out.println("Input a divider");
            String dividerStr = scanner.nextLine();

            if (dividerStr.equals("0")) {
                System.out.println("Divisor can't be zero!!");
                continue;
            }

            try {
                divisor = Integer.parseInt(dividerStr);

                System.out.println("Result: ");
                Divider divider = new Divider();

                if (Math.abs(dividend) < divisor)
                    System.out.println(dividend + "/" + divisor + " = " + dividend / divisor);
                else
                    System.out.println(divider.doDivision(dividend, divisor));

                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            }
        }

    }
}
