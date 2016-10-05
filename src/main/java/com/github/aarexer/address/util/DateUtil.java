package com.github.aarexer.address.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil
{
    private static final String  DATE_PATTERN = "dd.MM.yyyy";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate localDate)
    {
        if (localDate == null)
        {
            return null;
        }
        return DATE_FORMATTER.format(localDate);
    }

    public static LocalDate parse(String date)
    {
        try {
            return DATE_FORMATTER.parse(date, LocalDate::from);
        }
        catch (DateTimeException e)
        {
            return null;
        }
    }
    public static boolean validDate(String dateString)
    {
        return DateUtil.parse(dateString) != null;
    }
}
