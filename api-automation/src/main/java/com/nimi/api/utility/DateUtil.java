package com.nimi.api.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String getDateAndTime(Date date, String format) {
        String formattedDate = null;
        SimpleDateFormat myFormatObj = new SimpleDateFormat(format);
        formattedDate = myFormatObj.format(date);
        return formattedDate;
    }

    public static String getRelativeDate(String pattern, long days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getNextWeekDayDate(){
        String nextWeekDay=null;
        LocalDateTime date=LocalDateTime.now();
        do{
            date=date.plusDays(1);
        }while(date.getDayOfWeek().getValue()>5);
        nextWeekDay=date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return  nextWeekDay;
    }
}
