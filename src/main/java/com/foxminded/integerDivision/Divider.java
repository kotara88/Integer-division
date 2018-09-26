package com.foxminded.integerDivision;


import java.util.ArrayList;

public class Divider {

    public String doDivision(int dividend, int divisor) throws ArithmeticException {
        StringBuilder result = new StringBuilder();
        String[] digits = String.valueOf(Math.abs(dividend)).split("");
        StringBuilder reminder = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            createColumnOfDividing(divisor, reminder, digits, i, result);
        }
        modifyResultToPrint(dividend, divisor, result);
        if (dividend < 0) {
            result = modifyResultToPrintWhenDividendIsNegative(result);
        }
        return result.toString().trim();
    }

    private void createColumnOfDividing(int divisor,
                                        StringBuilder reminder, String[] digits, int i, StringBuilder result) {
        reminder.append(digits[i]);
        int reminderNumber = Integer.parseInt(reminder.toString());

        if (reminderNumber >= Math.abs(divisor)) {
            createIndent(divisor, reminderNumber, reminder, digits, i, result);
        }
    }

    private void createIndent(int divisor, Integer reminderNumber,
                              StringBuilder reminder, String[] digits, int i, StringBuilder result) {
        int resultOfMultiply = reminderNumber / divisor * divisor;
        int mod = reminderNumber % divisor;
        String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber);
        result.append(lastReminder).append("\n");
        String multiply = String.format("%" + (i + 2) + "d", resultOfMultiply);
        result.append(multiply).append("\n");
        Integer tab = lastReminder.length() - String.valueOf(resultOfMultiply).length();
        result.append(createSeparateLine(reminderNumber, tab)).append("\n");
        reminder.replace(0, reminder.length(), mod + "");
        reminderNumber = Integer.parseInt(reminder.toString());
        if (i == digits.length - 1) {
            result.append(String.format("%" + (i + 2) + "s", reminderNumber.toString())).append("\n");
        }
    }

    private String createSeparateLine(int reminderNumber, Integer tab) {
        return assembleString(tab, ' ') +
                assembleString(String.valueOf(reminderNumber).length(), '-');
    }

    private String assembleString(int numberOfSymbols, char symbol) {
        StringBuilder assembledString = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            assembledString.append(symbol);
        }
        return assembledString.toString();
    }

    private void modifyResultToPrint(Integer dividend, Integer divisor, StringBuilder result) {
        ArrayList<Integer> indexesOfLinesBreak = findIndexesOfLinesBreak(result);

        int tab = String.valueOf(dividend).length() + 1 - indexesOfLinesBreak.get(0);
        result.insert(indexesOfLinesBreak.get(2), assembleString(tab, ' ') +
                "│" + dividend / divisor);

        result.insert(indexesOfLinesBreak.get(1), assembleString(tab, ' ') +
                "│" + assembleString(String.valueOf(dividend / divisor).length(), '-'));

        result.insert(indexesOfLinesBreak.get(0), "│" + divisor);
        result.replace(1, indexesOfLinesBreak.get(0), dividend + "");
    }

    private StringBuilder modifyResultToPrintWhenDividendIsNegative(StringBuilder result) {
        ArrayList<Integer> indexesOfLinesBreak = findIndexesOfLinesBreak(result);
        for (int i = indexesOfLinesBreak.size() - 1; i >= 0; i--) {
            result.insert(indexesOfLinesBreak.get(i) + 1, " ");
        }
        result = new StringBuilder(result.toString().replace(" │", "│"));
        return result;
    }

    private ArrayList<Integer> findIndexesOfLinesBreak(StringBuilder result) {
        ArrayList<Integer> indexesOfLinesBreak = new ArrayList<Integer>();
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                indexesOfLinesBreak.add(i);
            }
        }
        return indexesOfLinesBreak;
    }
}
