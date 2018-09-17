package com.foxminded.integerDivision;


import java.math.BigInteger;
import java.util.ArrayList;

public class Dividing {

private StringBuilder result;

    public String makeDivision(BigInteger dividend, BigInteger divisor) {
        if (dividend.abs().compareTo(divisor) == -1)
            return dividend + "/" + divisor + " = " + dividend.divide(divisor);

        try {
            result = new StringBuilder();
            String[] digits = String.valueOf(dividend.abs()).split("");
            StringBuilder reminder = new StringBuilder();

            for (int i = 0; i < digits.length; i++) {
                makePostOfDividing(dividend, divisor, reminder, digits, i);
            }
            modifyResultToView(dividend, divisor);
            if (dividend.compareTo(BigInteger.ZERO) < 0){
                makeNormalViewIfDividendIsNegative();
            }
            return result.toString().trim();
        }catch (ArithmeticException e){
            return "Divisor can't be zero! Input again.";
        }
    }

    private String makeSeparateLine(BigInteger reminderNumber, BigInteger tab) {
        return assemblyString(tab, ' ') + assemblyString(new BigInteger(String.valueOf(calculateDigit(reminderNumber))), '-');
    }

    private void modifyResultToView(BigInteger dividend, BigInteger divisor) {
        ArrayList<Integer> indexesOfLinesBreak = getIndexesOfLinesBreak();
        BigInteger tab = new BigInteger(String.valueOf(calculateDigit(dividend) + 1 - indexesOfLinesBreak.get(0)));
        result.insert(indexesOfLinesBreak.get(2), assemblyString(tab, ' ') +"│" + dividend.divide(divisor));
        result.insert(indexesOfLinesBreak.get(1), assemblyString(tab, ' ') +"│" + assemblyString(new BigInteger(String.valueOf(dividend.divide(divisor).toString().length())), '-'));
        result.insert(indexesOfLinesBreak.get(0), "│" + divisor);
        result.replace(1, indexesOfLinesBreak.get(0), dividend.toString());
    }

    private void makeNormalViewIfDividendIsNegative() {
        ArrayList<Integer> indexesOfLinesBreak = getIndexesOfLinesBreak();
        for (int i = indexesOfLinesBreak.size() - 1; i >= 0; i--) {
            result.insert(indexesOfLinesBreak.get(i) + 1, " ");
        }
        result = new StringBuilder(result.toString().replace(" │", "│"));
    }

    private ArrayList<Integer> getIndexesOfLinesBreak(){
        ArrayList<Integer> indexesOfLinesBreak = new ArrayList<Integer>();
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                indexesOfLinesBreak.add(i);
            }
        }
        return indexesOfLinesBreak;
    }

    private int calculateDigit(BigInteger i) {
        return i.toString().length();
    }

    private String assemblyString(BigInteger numberOfSymbols, char symbol) {
        StringBuilder assembledSring = new StringBuilder();
        for (int i = 0; i < numberOfSymbols.intValue(); i++) {
            assembledSring.append(symbol);
        }
        return assembledSring.toString();
    }

    private void makePostOfDividing(BigInteger dividend, BigInteger divisor, StringBuilder reminder, String[] digits, int i){
        BigInteger mod;
        BigInteger resultOfMultiply;
        reminder.append(digits[i]);
        BigInteger reminderNumber = new BigInteger(reminder.toString());

        if (reminderNumber.compareTo(divisor.abs()) >= 0) {
            mod = reminderNumber.mod(divisor.abs());                         //остаток от деления
            resultOfMultiply = reminderNumber.divide(divisor).multiply(divisor);

            String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber.toString());
            result.append(lastReminder).append("\n");

            String multiply = String.format("%" + (i + 2) + "d", resultOfMultiply);
            result.append(multiply).append("\n");

            BigInteger tab = new BigInteger(String.valueOf(lastReminder.length() - calculateDigit(resultOfMultiply)));
            result.append(makeSeparateLine(reminderNumber, tab)).append("\n");

            reminder.replace(0, reminder.length(), mod.toString());
            reminderNumber = new BigInteger(reminder.toString());
        }

        if (i == digits.length - 1) {
            result.append(String.format("%" + (i + 2) + "s", reminderNumber.toString())).append("\n");
        }
    }
}
