package com.tiger.curious.guide.vmodel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;

import com.tiger.curious.guide.utils.PreferenceUtils;

import io.reactivex.Observer;

/**
 * Created by bkang016 on 9/20/17.
 */

public class DataSyncModel extends BaseObservable {

    private String ipAddress;

    private String port;

    public Activity mContext;

    public DataSyncModel(Activity context) {
        mContext = context;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        notifyChange();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
        notifyChange();
    }


    private void loadDefault() {
        setIpAddress(PreferenceUtils
                .getString(PreferenceUtils.PREF_DEFAULT_SERVER, "172.31.210.32"));
        setPort(PreferenceUtils
                .getString(PreferenceUtils.PREF_DEFAULT_PORT, "3333"));
    }

    public void onSure() {
        PreferenceUtils.putString(PreferenceUtils.PREF_DEFAULT_SERVER, getIpAddress());
        PreferenceUtils.putString(PreferenceUtils.PREF_DEFAULT_PORT, getPort());


    }

    public void onReset() {
        loadDefault();
    }
}
