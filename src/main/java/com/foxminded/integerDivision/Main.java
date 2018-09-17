package com.foxminded.integerDivision;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("If you want to exit input \"q\"");
        Scanner scanner = new Scanner(System.in);
        boolean mainFlag = true;

        while (mainFlag){
            boolean flag = true;
            BigInteger dividend = BigInteger.ZERO;

            while (flag) {
                System.out.println("Input a dividend");
                String dividendStr = scanner.nextLine();
                if (dividendStr.equals("q")) {
                mainFlag = false;
                break;
            }
            try {
                dividend = new BigInteger(dividendStr);
                flag = false;
            } catch (NumberFormatException e) {
                    System.out.println("Invalid number format! Input again.");
                }
            }

            if (mainFlag == false)
                break;

            flag = true;
            BigInteger divisor = BigInteger.ONE;

            while (flag) {
                System.out.println("Input a divider");
                String dividerStr = scanner.nextLine();

                if (dividerStr.equals("q")) {
                    mainFlag = false;
                    break;
                }

                try {
                    divisor = new BigInteger(dividerStr);
                    flag = false;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format! Input again.");
                }

            }

            if (mainFlag == false)
                break;

            System.out.println("Result: ");
            Dividing dividing = new Dividing();
            System.out.println(dividing.makeDivision(dividend, divisor));
            System.out.println();
        }
    }
}