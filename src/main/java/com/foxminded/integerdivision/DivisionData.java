package com.foxminded.integerdivision;

public class DivisionData {
    private int divisor;
    private int dividend;
    private int mod;
    private int multiplyResult;
    private int reminderNumber;
    private int index;
    private StringBuilder reminder;
    private StringBuilder result;
    private String[] digits;
    private String lastReminder;
    private boolean repeating;

    public DivisionData(int dividend, int divisor, String[] digits) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.digits = digits;
        this.reminder = new StringBuilder();
        this.result = new StringBuilder();
    }

    public DivisionData(int divisor) {
        this.index = 0;
        this.repeating = false;
        this.result = new StringBuilder();
        this.divisor = divisor;
    }

    public DivisionData(int dividend, int divisor, StringBuilder result) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.result = result;
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public int getMod() {
        return mod;
    }

    public int getReminderNumber() {
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

    public int getIndex() {
        return index;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public void setReminderNumber(int reminderNumber) {
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

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }
}
