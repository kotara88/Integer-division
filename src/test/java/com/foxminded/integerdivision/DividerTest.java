package com.foxminded.integerdivision;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class DividerTest {
    private Divider division;
    private int positiveDividend;
    private int negativeDividend;
    private int smallDividend;
    private int positiveDivisor;
    private int negativeDivisor;
    private int zeroDivisor;
    private String dividingProcessStringWithPositiveOperands;
    private String dividingProcessStringWithNegativeDividendAndPositiveDivisor;
    private String dividingProcessStringWithNegativeOperands;
    private String dividingProcessStringWithPositiveDividendAndNegativeDivisor;
    private String dividingProcessStringWithDividendLessThanDivisor;

    @Before
    public void initialize() {
        division = new Divider();
        negativeDividend = -1234;
        positiveDividend = 1234;
        smallDividend = 1;
        positiveDivisor = 12;
        negativeDivisor = -12;
        zeroDivisor = 0;
        dividingProcessStringWithPositiveOperands =
                "_1234│12\n" +
                        " 12  │---\n" +
                        " --  │102\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "   10";
        dividingProcessStringWithNegativeDividendAndPositiveDivisor =
                "_-1234│12\n" +
                        "  12  │----\n" +
                        "  --  │-102\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "    10";
        dividingProcessStringWithNegativeOperands =
                "_-1234│-12\n" +
                        "  12  │---\n" +
                        "  --  │102\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "    10";
        dividingProcessStringWithPositiveDividendAndNegativeDivisor =
                "_1234│-12\n" +
                        " 12  │----\n" +
                        " --  │-102\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "   10";

        dividingProcessStringWithDividendLessThanDivisor = "1 / 12 = 0";

    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendAndDivisorArePositive() {
        String expected = dividingProcessStringWithPositiveOperands;
        String actual = division.doDivision(positiveDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendIsNegativeAndDivisorIsPositive() {
        String expected = dividingProcessStringWithNegativeDividendAndPositiveDivisor;
        String actual = division.doDivision(negativeDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendAndDivisorAreNegative() {
        String expected = dividingProcessStringWithNegativeOperands;
        String actual = division.doDivision(negativeDividend, negativeDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendIsPositiveAndDivisorIsNegative() {
        String expected = dividingProcessStringWithPositiveDividendAndNegativeDivisor;
        String actual = division.doDivision(positiveDividend, negativeDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendLessThanDivisor() {
        String expected = dividingProcessStringWithDividendLessThanDivisor;
        String actual = division.doDivision(smallDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentException_WhenDivisorIsZero() {
        division.doDivision(positiveDividend, zeroDivisor);
    }

}
