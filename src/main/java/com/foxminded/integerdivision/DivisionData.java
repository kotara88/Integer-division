package com.foxminded.integerdivision;

public class DivisionData {
    private int divisor;
    private int mod;
    private int multiplyResult;
    private Integer reminderNumber;
    private StringBuilder reminder;
    private StringBuilder result;
    private String[] digits;
    private String lastReminder;

    public DivisionData(int divisor, String[] digits) {
        this.divisor = divisor;
        this.digits = digits;
        this.reminder = new StringBuilder();
        this.result = new StringBuilder();

    }

    public int getDivisor() {
        return divisor;
    }

    public int getMod() {
        return mod;
    }

    public Integer getReminderNumber() {
        return reminderNumber;
    }

    public StringBuilder getReminder() {
        return reminder;
    }

    public StringBuilder getResult() {
        return result;
    }


    public String[] getDigits() {
        return digits;
    }

    public int getMultiplyResult() {
        return multiplyResult;
    }

    public String getLastReminder() {
        return lastReminder;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public void setReminderNumber(Integer reminderNumber) {
        this.reminderNumber = reminderNumber;
    }

    public void setMultiplyResult(int multiplyResult) {
        this.multiplyResult = multiplyResult;
    }

    public void setLastReminder(String lastReminder) {
        this.lastReminder = lastReminder;
    }

    public void setResult(StringBuilder result) {
        this.result = result;
    }
}