package com.foxminded.integerDivision;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Division division = new Division();
        Scanner scanner = new Scanner(System.in);


        System.out.println("If you want to exit input \"q\"");

        while (true) {

            System.out.println("Input a dividend");
            String dividend = scanner.nextLine();
            if (dividend.equals("q"))
                break;

            System.out.println("Input a divisor");
            String divisor = scanner.nextLine();
            if (divisor.equals("q"))
                break;

            System.out.println("Result:");
            System.out.println(division.makeDivision(Integer.parseInt(dividend), Integer.parseInt(divisor)));
        }

    }
}
