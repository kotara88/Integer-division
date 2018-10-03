package com.foxminded.integerDivision;


import java.util.ArrayList;

public class Divider {

    public String doDivision(int dividend, int divisor) {

        if (divisor == 0)
            throw new IllegalArgumentException();

        StringBuilder result = new StringBuilder();
        String[] digits = String.valueOf(Math.abs(dividend)).split("");
        StringBuilder reminder = new StringBuilder();
        return createFinalResult(digits, dividend, divisor, reminder, result);
    }

    private String createFinalResult(String[] digits, int dividend, int divisor, StringBuilder reminder, StringBuilder result){
        for (int i = 0; i < digits.length; i++) {
            createDividingColumn(divisor, reminder, digits, i, result);
        }
        changeResultToPrint(dividend, divisor, result);
        if (dividend < 0) {
            result = changeResultToPrintNegativeDividend(result);
        }
        return result.toString().trim();
    }

    private void createDividingColumn(int divisor, StringBuilder reminder, String[] digits, int i, StringBuilder result) {
        reminder.append(digits[i]);
        int reminderNumber = Integer.parseInt(reminder.toString());

        if (reminderNumber >= Math.abs(divisor)) {
            createIndent(divisor, reminderNumber, reminder, digits, i, result);
        }
    }

    private void createIndent(int divisor, Integer reminderNumber, StringBuilder reminder,
                              String[] digits, int i, StringBuilder result) {
        int multiplyResult = reminderNumber / divisor * divisor;
        int mod = reminderNumber % divisor;
        String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber);
        result.append(lastReminder).append("\n");
        String tabForMultiplyResult = String.format("%" + (i + 2) + "d", multiplyResult);
        result.append(tabForMultiplyResult).append("\n");
        addSeparateLine(lastReminder, multiplyResult, reminderNumber, mod, reminder, digits, result, i);
    }

    private void addSeparateLine(String  lastReminder, int multiplyResult, Integer reminderNumber, int mod,
                                 StringBuilder reminder, String[] digits, StringBuilder result, int i){
        Integer tab = lastReminder.length() - String.valueOf(multiplyResult).length();
        result.append(createSeparateLine(reminderNumber, tab)).append("\n");
        reminder.replace(0, reminder.length(), mod + "");
        reminderNumber = Integer.parseInt(reminder.toString());
        if (i == digits.length - 1) {
            result.append(String.format("%" + (i + 2) + "s", reminderNumber.toString())).append("\n");
        }
    }

    private String createSeparateLine(int reminderNumber, Integer tab) {
        return assembleString(tab, ' ') + assembleString(String.valueOf(reminderNumber).length(), '-');
    }

    private String assembleString(int symbolsAmount, char symbol) {
        StringBuilder assembledString = new StringBuilder();
        for (int i = 0; i < symbolsAmount; i++) {
            assembledString.append(symbol);
        }
        return assembledString.toString();
    }

    private void changeResultToPrint(Integer dividend, Integer divisor, StringBuilder result) {
        ArrayList<Integer> LinesBreakIndexes = findLinesBreakIndexes(result);

        int tab = String.valueOf(dividend).length() + 1 - LinesBreakIndexes.get(0);
        result.insert(LinesBreakIndexes.get(2), assembleString(tab, ' ') +
                "│" + dividend / divisor);

        result.insert(LinesBreakIndexes.get(1), assembleString(tab, ' ') +
                "│" + assembleString(String.valueOf(dividend / divisor).length(), '-'));

        result.insert(LinesBreakIndexes.get(0), "│" + divisor);
        result.replace(1, LinesBreakIndexes.get(0), dividend + "");
    }

    private StringBuilder changeResultToPrintNegativeDividend(StringBuilder result) {
        ArrayList<Integer> linesBreakIndexes = findLinesBreakIndexes(result);
        for (int i = linesBreakIndexes.size() - 1; i >= 0; i--) {
            result.insert(linesBreakIndexes.get(i) + 1, " ");
        }
        return new StringBuilder(result.toString().replace(" │", "│"));
    }

    private ArrayList<Integer> findLinesBreakIndexes(StringBuilder result) {
        ArrayList<Integer> linesBreakIndexes = new ArrayList<Integer>();
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                linesBreakIndexes.add(i);
            }
        }
        return linesBreakIndexes;
    }
}
