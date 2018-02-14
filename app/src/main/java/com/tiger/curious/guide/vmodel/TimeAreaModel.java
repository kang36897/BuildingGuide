package com.tiger.curious.guide.vmodel;

import android.databinding.BaseObservable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bkang016 on 5/20/17.
 */

public class TimeAreaModel extends BaseObservable {

    final static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy年M月dd日 E HH:mm", Locale.SIMPLIFIED_CHINESE);
    private String date;

    private String week;

    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        notifyChange();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
        notifyChange();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyChange();
    }

    public void update(Date now) {
        String[] fields = FORMATTER.format(now).split("\\s+");

        setDate(fields[0]);
        setWeek(fields[1]);
        setTime(fields[2]);
    }
}
