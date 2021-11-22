package com.emirates.test.flightdetailsreactive.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isChristmasHoliDay(String flightDate) {
        Date date = stringToDateConversion(flightDate);
        Calendar curTime = dateToCalendarConversion(date);
        Calendar holidayStart = Calendar.getInstance();
        Calendar holidayEnd = Calendar.getInstance();
        setDate(holidayStart, Calendar.DECEMBER, 24);
        setDate(holidayEnd, Calendar.DECEMBER, 31);
        return curTime.after(holidayStart) && curTime.before(holidayEnd);
    }

    public static void setDate(Calendar cal, int month, int date) {
        cal.clear();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, date);
    }

   public static boolean isWeekend(String flightDate) {
        Date date = stringToDateConversion(flightDate);
        Calendar curTime = dateToCalendarConversion(date);
        System.out.println(curTime.get(Calendar.DAY_OF_WEEK));
        if ((curTime.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                || (Calendar.DAY_OF_WEEK == Calendar.SUNDAY)) {
            return true;
        } else {
            return false;
        }
    }

    public static Date stringToDateConversion(String inputDateStr) {
        Date date = null;
        try {
            date = sdf.parse(inputDateStr);
        } catch (Exception e) {
        }
        return date;
    }

    public static Calendar dateToCalendarConversion(Date inputDate) {
        Calendar curTime = Calendar.getInstance();
        curTime.setTime(inputDate);
        return curTime;
    }
}
