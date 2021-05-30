package org.example;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.example.DateTimeApiTraining.countClockDigitsWhereSumEquals15;
import static org.example.DateTimeApiTraining.countLeapYears;
import static org.example.DateTimeApiTraining.dayOfTheYear;
import static org.example.DateTimeApiTraining.countDaysBetweenTwoDates;
import static org.junit.Assert.assertEquals;

public class DateTimeApiTrainingTest {

    @Test
    public void countClockDigitsWhereSumEquals15Test() {
        final LocalTime START_HOUR = LocalTime.parse("11:45");
        final LocalTime END_HOUR = LocalTime.parse("22:30");

        final int result = countClockDigitsWhereSumEquals15(START_HOUR, END_HOUR);

        assertEquals(47, result);
    }

    @Test
    public void countLeapYearsTest() {
        final LocalDate ENDING_DATE = LocalDate.of(2021, Month.FEBRUARY, 6);
        final LocalDate ENDING_DATE_2 = LocalDate.of(2024, Month.FEBRUARY, 29);

        assertEquals(6, countLeapYears(LocalDate.of(1997, Month.APRIL, 6), ENDING_DATE));
        assertEquals(7, countLeapYears(LocalDate.of(1996, Month.FEBRUARY, 7), ENDING_DATE));
        assertEquals(6, countLeapYears(LocalDate.of(1996, Month.MARCH, 6), ENDING_DATE));
        assertEquals(7, countLeapYears(LocalDate.of(1996, Month.FEBRUARY, 29), ENDING_DATE));
        assertEquals(6, countLeapYears(LocalDate.of(1996, Month.MARCH, 1), ENDING_DATE));
        assertEquals(7, countLeapYears(LocalDate.of(1996, Month.FEBRUARY, 28), ENDING_DATE));
        assertEquals(8, countLeapYears(LocalDate.of(1996, Month.FEBRUARY, 29), ENDING_DATE_2));
    }


    @Test
    public void dayOfTheYearTest() {
        assertEquals(LocalDate.of(2016, 3, 8), dayOfTheYear(68, 2016));
        assertEquals(LocalDate.of(1996, 3, 8), dayOfTheYear(68, 1996));
        assertEquals(LocalDate.of(1997, 3, 9), dayOfTheYear(68, 1997));
        assertEquals(LocalDate.of(2016, 7, 8), dayOfTheYear(190, 2016));
    }

    @Test
    public void countDaysBetweenTwoDatesTest() {
        final LocalDate START_DATE_OF_WW2 = LocalDate.of(1939, Month.SEPTEMBER, 1);
        final LocalDate END_DATE_OF_WW2 = LocalDate.of(1945, Month.SEPTEMBER, 2);

        assertEquals(2194, countDaysBetweenTwoDates(START_DATE_OF_WW2, END_DATE_OF_WW2));
    }
}
