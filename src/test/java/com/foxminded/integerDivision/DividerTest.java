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
    public void mustMakeDivision_WhenDividendAndDivisorArePositive() {
        String expected = whenDividendAndDivisorArePositive;
        String actual = division.makeDivision(positiveDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustMakeDivision_WhenDividendIsNegativeAndDivisorIsPositive() {
        String expected = whenDividendIsNegativeAndDivisorIsPositive;
        String actual = division.makeDivision(negativeDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustMakeDivision_WhenDividendAndDivisorAreNegative() {
        String expected = whenDividendAndDivisorAreNegative;
        String actual = division.makeDivision(negativeDividend, negativeDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustMakeDivision_WhenDividendIsPositiveAndDivisorIsNegative() {
        String expected = whenDividendIsPositiveAndDivisorIsNegative;
        String actual =  division.makeDivision(positiveDividend, negativeDivisor);
        assertEquals(expected,actual);
    }

}
