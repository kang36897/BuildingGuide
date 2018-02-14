package com.tiger.curious.guide.vmodel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.support.v4.app.DialogFragment;

import com.tiger.curious.guide.fragment.ProgressFragment;
import com.tiger.curious.guide.utils.PreferenceUtils;

import io.reactivex.Observer;
import io.reactivex.internal.operators.parallel.ParallelRunOn;

/**
 * Created by bkang016 on 9/20/17.
 */

public class DataSyncModel extends BaseObservable {

    private String ipAddress;

    private String port;

    public DialogFragment mUpdatePanel;

    public DataSyncModel(DialogFragment panel) {
        mUpdatePanel = panel;
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

        mUpdatePanel.dismiss();

        ProgressFragment.newInstance(getIpAddress(), getPort())
                .show(mUpdatePanel.getFragmentManager(), ProgressFragment.TAG);
    }

    public void onReset() {
        loadDefault();
    }
}
