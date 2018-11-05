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
    private String stringWithPositiveOperands;
    private String stringWithNegativeDividendAndPositiveDivisor;
    private String stringWithNegativeOperands;
    private String stringWithPositiveDividendAndNegativeDivisor;
    private String stringWithDividendLessThanDivisor;

    @Before
    public void initialize() {
        division = new Divider();
        negativeDividend = -1234;
        positiveDividend = 1234;
        smallDividend = 7;
        positiveDivisor = 12;
        negativeDivisor = -12;
        zeroDivisor = 0;
        stringWithPositiveOperands =
                "_1234│12\n" +
                        " 12  │--------\n" +
                        " --  │102.8(3)\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "  _100\n" +
                        "    96\n" +
                        "    ---\n" +
                        "    _40\n" +
                        "     36\n" +
                        "     --\n" +
                        "      4";
        stringWithNegativeDividendAndPositiveDivisor =
                "_-1234│12\n" +
                        "  12  │---------\n" +
                        "  --  │-102.8(3)\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "   _100\n" +
                        "     96\n" +
                        "     ---\n" +
                        "     _40\n" +
                        "      36\n" +
                        "      --\n" +
                        "       4";
        stringWithNegativeOperands =
                "_-1234│-12\n" +
                        "  12  │--------\n" +
                        "  --  │102.8(3)\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "   _100\n" +
                        "     96\n" +
                        "     ---\n" +
                        "     _40\n" +
                        "      36\n" +
                        "      --\n" +
                        "       4";
        stringWithPositiveDividendAndNegativeDivisor =
                "_1234│-12\n" +
                        " 12  │---------\n" +
                        " --  │-102.8(3)\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "  _100\n" +
                        "    96\n" +
                        "    ---\n" +
                        "    _40\n" +
                        "     36\n" +
                        "     --\n" +
                        "      4";

        stringWithDividendLessThanDivisor =
                "_7 │12\n" +
                        " 60│-------\n" +
                        " --│0.58(3)\n" +
                        "_100\n" +
                        "  96\n" +
                        "  ---\n" +
                        "  _40\n" +
                        "   36\n" +
                        "   --\n" +
                        "    4";
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendAndDivisorArePositive() {
        String expected = stringWithPositiveOperands;
        String actual = division.doDivision(positiveDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendIsNegativeAndDivisorIsPositive() {
        String expected = stringWithNegativeDividendAndPositiveDivisor;
        String actual = division.doDivision(negativeDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendAndDivisorAreNegative() {
        String expected = stringWithNegativeOperands;
        String actual = division.doDivision(negativeDividend, negativeDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendIsPositiveAndDivisorIsNegative() {
        String expected = stringWithPositiveDividendAndNegativeDivisor;
        String actual = division.doDivision(positiveDividend, negativeDivisor);
        assertEquals(expected, actual);
    }

    @Test
    public void mustCreateCorrectDividingProcessString_WhenDividendLessThanDivisor() {
        String expected = stringWithDividendLessThanDivisor;
        String actual = division.doDivision(smallDividend, positiveDivisor);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowIllegalArgumentException_WhenDivisorIsZero() {
        division.doDivision(positiveDividend, zeroDivisor);
    }
}