package com.tiger.curious.guide.utils;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by bkang016 on 5/20/17.
 */
public class TimeFormatterTest {
    @Test
    public void format() throws Exception {

        System.out.println(new SimpleDateFormat("YYYY年M月dd日 E HH:mm", Locale.SIMPLIFIED_CHINESE).format(new Date()));
        System.out.println(DateFormat.getDateInstance(DateFormat.LONG, Locale.SIMPLIFIED_CHINESE).format(new Date()));

        System.out.println(TimeFormatter.format(new Date(), "E"));
    }

}