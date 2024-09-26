package com.epam.engx.cleancode.dry.thirdpartyjar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalculator {

    private static final int LEAP_YEAR_SHIFT = 1;

    public static int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(from);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(to);
        int diffYear = getYearsDifference(endCalendar, startCalendar);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR)) {
            return diffYear - 1;
        }
        return diffYear;
    }

    private static int getYearsDifference(Calendar endCalendar, Calendar startCalendar) {
        return endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
    }
}
