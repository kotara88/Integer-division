package com.foxminded.integerDivision;


import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class DivisionTest {

    Division division;
    int evenDividendIsBiggerThanDivisor;
    int oddDividendIsBiggerThanDivisor;
    int dividendIsLessThanDivisor;
    int divisor;
    int zeroDivisor;

    @Before
    public void initialize(){
        division = new Division();
        evenDividendIsBiggerThanDivisor = 147;
        oddDividendIsBiggerThanDivisor = 146;
        dividendIsLessThanDivisor = 1;
        divisor = 2;
        zeroDivisor = 0;

    }

    @Test
    public void mustMakeDivision_WhenEvenDividendIsBiggerThanDivisor() {
        String expected =   "_147│2\n" +
                " 14 │--\n" +
                " -- │73\n" +
                "  _7\n" +
                "   6\n" +
                "   -\n" +
                "   1\n";
        assertEquals(expected, division.makeDivision(evenDividendIsBiggerThanDivisor, divisor));
    }

    @Test
    public void mustMakeDivision_WhenOddDividendIsBiggerThanDivisor() {
        String expected =   "_146│2\n" +
                " 14 │--\n" +
                " -- │73\n" +
                "  _6\n" +
                "   6\n" +
                "   -\n" +
                "   0\n";
        assertEquals(expected, division.makeDivision(oddDividendIsBiggerThanDivisor, divisor));
    }

    @Test
    public void mustMakeDivision_WhenDividendIsLessThanDivisor() {
        String expected =  "1/2=0" ;
        assertEquals(expected, division.makeDivision(dividendIsLessThanDivisor, divisor));
    }

    @Test
    public void mustMakeWarning_WhenDividendIsZero() {
        String expected =  "Divisor cannot be 0" ;
        assertEquals(expected, division.makeDivision(dividendIsLessThanDivisor, zeroDivisor));
    }
}
