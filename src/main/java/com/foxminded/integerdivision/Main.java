package com.foxminded.integerdivision;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        Divider divider = new Divider();
        Scanner scanner = new Scanner(System.in);
        int divisor;
        int dividend;

        while (flag) {
            System.out.println("Input dividend:");
            try {
                dividend = scanner.nextInt();
                System.out.println("Input divisor:");
                divisor = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number format!");
                scanner.next();
                continue;
            }

            if (divisor == 0) {
                System.out.println("Divisor can't be zero!!");
                continue;
            }
            System.out.println("Result: ");
            System.out.println(divider.doDivision(dividend, divisor) + "\n");
            System.out.println("If you want to close program input 'q'");
            String userResponse = scanner.next();
            if (userResponse.equals("q"))
                flag = false;
        }
    }
}
