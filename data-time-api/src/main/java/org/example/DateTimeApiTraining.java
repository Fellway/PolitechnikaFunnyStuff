package org.example;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

public class DateTimeApiTraining {

    public static long countDaysBetweenTwoDates(final LocalDate beginningDate, final LocalDate endingDate) {
        return ChronoUnit.DAYS.between(beginningDate, endingDate) + 1;
    }

    public static LocalDate dayOfTheYear(final Integer numberOfDay, final Integer year) {
        return Year.of(year).atDay(numberOfDay);
    }

    public static int countLeapYears(final LocalDate beginningDate, final LocalDate endingDate) {
        final int currentYear = endingDate.getYear();
        int numberOfLeapYears = 0;
        for (int i = 0; i <= currentYear - beginningDate.getYear(); i++) {
            if (beginningDate.plusYears(i).isLeapYear()) {
                numberOfLeapYears++;
            }
        }

        if (beginningDate.isLeapYear() && beginningDate.isAfter(LocalDate.of(beginningDate.getYear(), Month.FEBRUARY, 29))) {
            numberOfLeapYears--;
        }

        if (endingDate.isLeapYear() && endingDate.isBefore(LocalDate.of(currentYear, Month.FEBRUARY, 29))) {
            numberOfLeapYears--;
        }

        return numberOfLeapYears;
    }

    public static int countClockDigitsWhereSumEquals15(final LocalTime beginningTime, final LocalTime endingTime) {
        int l = (int) Duration.between(beginningTime, endingTime).toMinutes();
        int counter = 0;
        int hour = beginningTime.getHour();
        int minute;
        for (int i = beginningTime.getMinute(); i < l; i++) {
            minute = i % 60;
            if (minute == 0) {
                hour++;
            }

            final int sumOfDigits = IntStream.of(hour / 10, hour % 10, minute / 10, minute % 10).sum();
            if (sumOfDigits == 15) {
                counter++;
            }
        }
        return counter;
    }
}
