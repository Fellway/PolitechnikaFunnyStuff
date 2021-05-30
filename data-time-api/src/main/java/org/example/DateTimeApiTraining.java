package org.example;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

public class DateTimeApiTraining {

    private static final LocalDate START_DATE_OF_WW2 = LocalDate.of(1939, Month.SEPTEMBER, 1);
    private static final LocalDate END_DATE_OF_WW2 = LocalDate.of(1945, Month.SEPTEMBER, 2);
    private static final LocalDate MY_BIRTHDAY_DATE = LocalDate.of(1996, Month.FEBRUARY, 6);
    private static final Integer NUMBER_OF_DAY = 68;
    private static final LocalTime START_HOUR = LocalTime.parse("11:45");
    private static final LocalTime END_HOUR = LocalTime.parse("22:30");

    public static void main(String[] args) {

        System.out.println(countDaysBetweenTwoDates(START_DATE_OF_WW2, END_DATE_OF_WW2));
        System.out.println(dayOfTheYear2016ToLocalDate(NUMBER_OF_DAY));
        System.out.println(countClockDigitsWhereSumEquals15(START_HOUR, END_HOUR));
        System.out.println(countLeapYears(MY_BIRTHDAY_DATE));
    }

    public static Long countDaysBetweenTwoDates(final LocalDate beginningDate, final LocalDate endingDate) {
        return ChronoUnit.DAYS.between(beginningDate, endingDate);
    }

    public static LocalDate dayOfTheYear2016ToLocalDate(final Integer numberOfDay) {
        return Year.of(2016).atDay(numberOfDay);
    }

    public static int countLeapYears(final LocalDate beginningDate) {
        final int currentYear = LocalDate.now().getYear();
        int numberOfLeapYears = 0;
        for (int i = 0; i < currentYear - beginningDate.getYear(); i++) {
            if (beginningDate.plusYears(i).isLeapYear()) {
                numberOfLeapYears++;
            }
        }

        if (beginningDate.isLeapYear() && beginningDate.isAfter(LocalDate.of(beginningDate.getYear(), Month.FEBRUARY, 29))) {
            numberOfLeapYears--;
        }

        if (LocalDate.now().isLeapYear() && LocalDate.now().isBefore(LocalDate.of(LocalDate.now().getYear(), Month.FEBRUARY, 29))) {
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

            final IntStream hoursDigitsStream = IntStream.of(hour / 10, hour % 10);
            final IntStream minutesDigitsStream = IntStream.of(minute / 10, minute % 10);
            int sum = IntStream.concat(hoursDigitsStream, minutesDigitsStream).sum();
            if (sum == 15) {
                counter++;
            }
        }
        return counter;
    }
}
