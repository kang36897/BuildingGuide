package com.tiger.curious.guide.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bkang016 on 5/20/17.
 */

public class TimeFormatter {

    final static SimpleDateFormat DEFAULT_FORMATTER = new SimpleDateFormat();


    public static synchronized String format(Date date, String patten) {

        DEFAULT_FORMATTER.applyPattern(patten);
        return DEFAULT_FORMATTER.format(date);

    }

}
