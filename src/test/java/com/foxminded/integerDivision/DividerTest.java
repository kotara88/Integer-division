package com.foxminded.integerDivision;


import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class DividerTest {

    private Divider division;
    private int positiveDividend;
    private int negativeDividend;
    private int positiveDivisor;
    private int negativeDivisor;
    private String whenDividendAndDivisorArePositive;
    private String whenDividendIsNegativeAndDivisorIsPositive;
    private String whenDividendAndDivisorAreNegative;
    private String whenDividendIsPositiveAndDivisorIsNegative;

    @Before
    public void initialize(){
        division = new Divider();
        negativeDividend = -1234;
        positiveDividend = 1234;
        positiveDivisor = 12;
        negativeDivisor = -12;
        whenDividendAndDivisorArePositive =
                "_1234│12\n" +
                        " 12  │---\n" +
                        " --  │102\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "   10";
        whenDividendIsNegativeAndDivisorIsPositive =
                "_-1234│12\n" +
                        "  12  │----\n" +
                        "  --  │-102\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "    10";
        whenDividendAndDivisorAreNegative =
                "_-1234│-12\n" +
                        "  12  │---\n" +
                        "  --  │102\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "    10";
        whenDividendIsPositiveAndDivisorIsNegative =
                "_1234│-12\n" +
                        " 12  │----\n" +
                        " --  │-102\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "   10";
    }


    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendAndDivisorArePositive() {
        String expected = whenDividendAndDivisorArePositive;
        String actual = division.doDivision(positiveDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendIsNegativeAndDivisorIsPositive() {
        String expected = whenDividendIsNegativeAndDivisorIsPositive;
        String actual = division.doDivision(negativeDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendAndDivisorAreNegative() {
        String expected = whenDividendAndDivisorAreNegative;
        String actual = division.doDivision(negativeDividend, negativeDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendIsPositiveAndDivisorIsNegative() {
        String expected = whenDividendIsPositiveAndDivisorIsNegative;
        String actual =  division.doDivision(positiveDividend, negativeDivisor);
        assertEquals(expected,actual);
    }

}
