package com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.HashMap;

public class Divider {

    public String doDivision(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException();
        }
        if (dividend == 0) {
            return "0";
        }
        String[] digits = String.valueOf(Math.abs(dividend)).split("");
        return createDivisionString(digits, dividend, divisor);
    }

    private String createDivisionString(String[] digits, int dividend, int divisor) {
        DivisionData data = new DivisionData(dividend, divisor, digits);
        createDividingColumn(data);
        data.setResult(new StringBuilder(addOperandsToDividingString(dividend, divisor, data.getResult().toString())));
        if (dividend < 0) {
            data.setResult(new StringBuilder(createDivisionStringWithNegativeDividend(data.getResult().toString())));
        }
        return data.getResult().toString().trim();
    }

    private void createDividingColumn(DivisionData data) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        boolean flag = true;
        int count = 0;
        while (flag) {
            try {
                data.getReminder().append(data.getDigits()[count]);
                data.setReminderNumber(Integer.parseInt(data.getReminder().toString()));
            } catch (ArrayIndexOutOfBoundsException e) {
                data.setReminderNumber(Integer.parseInt(data.getReminder().append(0).toString()));
                if (list.contains(data.getReminderNumber())) {
                    data.getResult().append(String.format("%" + (count + 1) + "s", data.getReminderNumber() / 10)).append("\n");
                    break;
                } else {
                    list.add(data.getReminderNumber());
                }
            }
            if (data.getReminderNumber() >= Math.abs(data.getDivisor())) {
                createIndent(data, count);
            }
            count++;
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
        if (String.valueOf(data.getMultiplyResult()).length() == 1 && String.valueOf(data.getReminderNumber()).length() == 1) {
            data.getResult().append(createSeparateLine(data.getReminderNumber(), tab)).append("\n");
        } else if (String.valueOf(data.getMultiplyResult()).length() == 1) {
            data.getResult().append(createSeparateLine(data.getReminderNumber(), tab - 1)).append("\n");
        } else {
            data.getResult().append(createSeparateLine(data.getReminderNumber(), tab)).append("\n");
        }
        data.getReminder().replace(0, data.getReminder().length(), data.getMod() + "");
        data.setReminderNumber(Integer.parseInt(data.getReminder().toString()));

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

    private String addOperandsToDividingString(Integer dividend, Integer divisor, String result) {
        String divisionResult = calculateFraction(dividend, divisor);
        ArrayList<Integer> linesBreakIndexes = findLinesBreakIndexes(result);
        StringBuilder tempString = new StringBuilder(result);
        int tab = String.valueOf(dividend).length() + 1 - linesBreakIndexes.get(0);
        tempString.insert(linesBreakIndexes.get(2), assembleString(tab, ' ') +
                "│" + divisionResult);
        tempString.insert(linesBreakIndexes.get(1), assembleString(tab, ' ') +
                "│" + assembleString(divisionResult.length(), '-'));
        tempString.insert(linesBreakIndexes.get(0), "│" + divisor);
        if (dividend < divisor) {
            tempString.replace(1, linesBreakIndexes.get(0), dividend + " ").toString();
        } else {
            tempString.replace(1, linesBreakIndexes.get(0), String.valueOf(dividend)).toString();
        }
        return tempString.toString();
    }

    private String createDivisionStringWithNegativeDividend(String result) {
        StringBuilder tempString = new StringBuilder(result);
        ArrayList<Integer> linesBreakIndexes = findLinesBreakIndexes(result);
        for (int i = linesBreakIndexes.size() - 1; i >= 0; i--) {
            tempString.insert(linesBreakIndexes.get(i) + 1, " ");
        }
        return tempString.toString().replace(" │", "│");
    }

    private ArrayList<Integer> findLinesBreakIndexes(String result) {
        ArrayList<Integer> linesBreakIndexes = new ArrayList<Integer>();
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                linesBreakIndexes.add(i);
            }
        }
        return linesBreakIndexes;
    }

    private String calculateFraction(int dividend, int divisor) {
        if (dividend == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        result.append(calculateTheDivisionPartBeforePoint(dividend, divisor));
        result.append(".");
        result.append(calculateTheDivisionPartAfterPoint(dividend, divisor));
        return result.toString();
    }

    private String calculateTheDivisionPartBeforePoint(int dividend, int divisor) {
        StringBuilder result = new StringBuilder();
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        if (sign == -1) {
            result.append("-");
        }
        int initial = dividend / divisor;
        result.append(String.valueOf(initial));
        if (dividend % divisor == 0) {
            return result.toString();
        }
        return result.toString();
    }

    private String calculateTheDivisionPartAfterPoint(int dividend, int divisor) {
        StringBuilder result = new StringBuilder();
        int reminder = dividend % divisor;
        HashMap<Integer, Integer> reminders = new HashMap<Integer, Integer>();
        int index = 0;
        boolean repeating = false;
        while (reminder > 0 && !repeating) {
            if (reminders.containsKey(reminder)) {
                index = reminders.get(reminder);
                repeating = true;
                break;
            } else
                reminders.put(reminder, result.length());

            reminder = reminder * 10;
            int temp = reminder / divisor;
            result.append(temp);
            reminder = reminder % divisor;
        }
        if (repeating) {
            result.append(")");
            result.insert(index, "(");
        }
        return result.toString();
    }
}