package com.foxminded.integerDivision;


import java.util.ArrayList;

public class Divider {

    private StringBuilder result;

    public String makeDivision(int dividend, int divisor) throws ArithmeticException {
        result = new StringBuilder();
        String[] digits = String.valueOf(Math.abs(dividend)).split("");
        StringBuilder reminder = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            makeColumnOfDividing(divisor, reminder, digits, i);
        }
        modifyResultToPrint(dividend, divisor);
        if (dividend < 0) {
            modifyResultToPrintWhenDividendIsNegative();
        }
        return result.toString().trim();
    }

    private void makeColumnOfDividing(int divisor,
                                      StringBuilder reminder, String[] digits, int i) {
        reminder.append(digits[i]);
        int reminderNumber = Integer.parseInt(reminder.toString());

        if (reminderNumber >= Math.abs(divisor)) {
            makeIndent(divisor, reminderNumber, reminder, digits,i);
        }
    }

    private void makeIndent(int divisor, Integer reminderNumber,
                            StringBuilder reminder, String[] digits, int i){
        int resultOfMultiply = reminderNumber / divisor * divisor;
        int mod = reminderNumber % divisor;
        String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber);
        result.append(lastReminder).append("\n");
        String multiply = String.format("%" + (i + 2) + "d", resultOfMultiply);
        result.append(multiply).append("\n");
        Integer tab = lastReminder.length() - calculateDigit(resultOfMultiply);
        result.append(makeSeparateLine(reminderNumber, tab)).append("\n");
        reminder.replace(0, reminder.length(), mod + "");
        reminderNumber = Integer.parseInt(reminder.toString());
        if (i == digits.length - 1) {
            result.append(String.format("%" + (i + 2) + "s", reminderNumber.toString())).append("\n");
        }
    }

    private int calculateDigit(int i) {
        return String.valueOf(i).length();
    }

    private String makeSeparateLine(int reminderNumber, Integer tab) {
        return assembleString(tab, ' ') +
                assembleString(calculateDigit(reminderNumber), '-');
    }

    private String assembleString(int numberOfSymbols, char symbol) {
        StringBuilder assembledString = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            assembledString.append(symbol);
        }
        return assembledString.toString();
    }

    private void modifyResultToPrint(Integer dividend, Integer divisor) {
        ArrayList<Integer> indexesOfLinesBreak = getIndexesOfLinesBreak();

        int tab = calculateDigit(dividend) + 1 - indexesOfLinesBreak.get(0);
        result.insert(indexesOfLinesBreak.get(2), assembleString(tab, ' ') +
                "│" + dividend / divisor);

        result.insert(indexesOfLinesBreak.get(1), assembleString(tab, ' ') +
                "│" + assembleString( calculateDigit(dividend / divisor), '-'));

        result.insert(indexesOfLinesBreak.get(0), "│" + divisor);
        result.replace(1, indexesOfLinesBreak.get(0), dividend + "");
    }

    private void modifyResultToPrintWhenDividendIsNegative() {
        ArrayList<Integer> indexesOfLinesBreak = getIndexesOfLinesBreak();
        for (int i = indexesOfLinesBreak.size() - 1; i >= 0; i--) {
            result.insert(indexesOfLinesBreak.get(i) + 1, " ");
        }
        result = new StringBuilder(result.toString().replace(" │", "│"));
    }

    private ArrayList<Integer> getIndexesOfLinesBreak() {
        ArrayList<Integer> indexesOfLinesBreak = new ArrayList<Integer>();
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                indexesOfLinesBreak.add(i);
            }
        }
        return indexesOfLinesBreak;
    }
}
