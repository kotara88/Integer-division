package com.foxminded.integerdivision;

import java.util.ArrayList;

public class Divider {

    public String doDivision(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException();
        }
        String[] digits = String.valueOf(Math.abs(dividend)).split("");
        return createDivisionString(digits, dividend, divisor);
    }

    private String createDivisionString(String[] digits, int dividend, int divisor) {
        DivisionData data = new DivisionData(divisor, digits);
        for (int i = 0; i < digits.length; i++) {
            createDividingColumn(data, i);
        }
        addOperandsToDividingString(dividend, divisor, data.getResult());
        if (dividend < 0) {
            data.setResult(createDivisionStringWhenNegativeDividend(data.getResult()));
        }
        return data.getResult().toString().trim();
    }

    private void createDividingColumn(DivisionData data, int i) {
        data.getReminder().append(data.getDigits()[i]);
        data.setReminderNumber(Integer.parseInt(data.getReminder().toString()));

        if (data.getReminderNumber() >= Math.abs(data.getDivisor())) {
            createIndent(data, i);
        }
    }

    private void createIndent(DivisionData data, int i) {
        data.setMultiplyResult(data.getReminderNumber() / data.getDivisor() * data.getDivisor());
        data.setMod(data.getReminderNumber() % data.getDivisor());
        data.setLastReminder(String.format("%" + (i + 2) + "s", "_" + data.getReminderNumber()));
        data.getResult().append(data.getLastReminder()).append("\n");
        String tabForMultiplyResult = String.format("%" + (i + 2) + "d", data.getMultiplyResult());
        data.getResult().append(tabForMultiplyResult).append("\n");
        addSeparateLine(data, i);
    }

    private void addSeparateLine(DivisionData data, int i) {
        Integer tab = data.getLastReminder().length() - String.valueOf(data.getMultiplyResult()).length();
        data.getResult().append(createSeparateLine(data.getReminderNumber(), tab)).append("\n");
        data.getReminder().replace(0, data.getReminder().length(), data.getMod() + "");
        data.setReminderNumber(Integer.parseInt(data.getReminder().toString()));
        if (i == data.getDigits().length - 1) {
            data.getResult().append(String.format("%" + (i + 2) + "s", data.getReminderNumber().toString())).append("\n");
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

    private void addOperandsToDividingString(Integer dividend, Integer divisor, StringBuilder result) {
        ArrayList<Integer> linesBreakIndexes = findLinesBreakIndexes(result);

        int tab = String.valueOf(dividend).length() + 1 - linesBreakIndexes.get(0);
        result.insert(linesBreakIndexes.get(2), assembleString(tab, ' ') +
                "│" + dividend / divisor);

        result.insert(linesBreakIndexes.get(1), assembleString(tab, ' ') +
                "│" + assembleString(String.valueOf(dividend / divisor).length(), '-'));

        result.insert(linesBreakIndexes.get(0), "│" + divisor);
        result.replace(1, linesBreakIndexes.get(0), dividend + "");
    }

    private StringBuilder createDivisionStringWhenNegativeDividend(StringBuilder result) {
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
