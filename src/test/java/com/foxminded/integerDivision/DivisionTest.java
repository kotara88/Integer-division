package com.foxminded.integerDivision;


import org.junit.Test;
import org.junit.Before;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class DivisionTest {

    Dividing division;
    BigInteger positiveDividend;
    BigInteger negativeDividend;
    BigInteger positiveDivisor;
    BigInteger negativeDivisor;
    BigInteger zeroDivisor;
    BigInteger dividendIsLessThanDivisor;
    String expectedWhenDividendIsPositive;
    String expectedWhenDividendIsNegative;
    String expectedWhenDividendAndDivisorAreNegative;
    String expectedWhenDividendIsPositiveAndDivisorIsNegative;
    String expectedWhenDivisorIsZero;
    String expectedWhenDividendIsLessThanDivisor;

    @Before
    public void initialize(){
        division = new Dividing();
        negativeDividend = new BigInteger("-1234");
        positiveDividend = new BigInteger("1234");
        positiveDivisor = new BigInteger("12");
        negativeDivisor = new BigInteger("-12");
        zeroDivisor = BigInteger.ZERO;
        dividendIsLessThanDivisor = new BigInteger("1");
        expectedWhenDividendIsPositive =
                "_1234│12\n" +
                        " 12  │---\n" +
                        " --  │102\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "   10";
        expectedWhenDividendIsNegative =
                "_-1234│12\n" +
                        "  12  │----\n" +
                        "  --  │-102\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "    10";
        expectedWhenDividendAndDivisorAreNegative =
                "_-1234│-12\n" +
                        "  12  │---\n" +
                        "  --  │102\n" +
                        "   _34\n" +
                        "    24\n" +
                        "    --\n" +
                        "    10";
        expectedWhenDividendIsPositiveAndDivisorIsNegative =
                "_1234│-12\n" +
                        " 12  │----\n" +
                        " --  │-102\n" +
                        "  _34\n" +
                        "   24\n" +
                        "   --\n" +
                        "   10";
        expectedWhenDivisorIsZero = "Divisor can't be zero! Input again.";
        expectedWhenDividendIsLessThanDivisor =  "1/12 = 0" ;

    }


    @Test
    public void mustMakeDivision_WhenDividendAndDivisorArePositive() {
        assertEquals(expectedWhenDividendIsPositive, division.makeDivision(positiveDividend, positiveDivisor));
    }

    @Test
    public void mustMakeDivision_WhenDividendIsNegativeAndDivisorIsPositive() {
        assertEquals(expectedWhenDividendIsNegative, division.makeDivision(negativeDividend, positiveDivisor));
    }

    @Test
    public void mustMakeDivision_WhenDividendAndDivisorAreNegative() {
        assertEquals(expectedWhenDividendAndDivisorAreNegative, division.makeDivision(negativeDividend, negativeDivisor));
    }

    @Test
    public void mustMakeDivision_WhenDividendIsPositiveAndDivisorIsNegative() {
        assertEquals(expectedWhenDividendIsPositiveAndDivisorIsNegative, division.makeDivision(positiveDividend, negativeDivisor));
    }

    @Test
    public void mustMakeDivision_expectedWhenDividendIsZero() {
        assertEquals(expectedWhenDivisorIsZero, division.makeDivision(positiveDividend, zeroDivisor));
    }

    @Test
    public void mustMakeDivision_WhenDividendIsLessThanDivisor() {
        assertEquals(expectedWhenDividendIsLessThanDivisor, division.makeDivision(dividendIsLessThanDivisor, positiveDivisor));
    }

}
