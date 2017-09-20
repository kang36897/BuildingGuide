package com.tiger.curious.guide.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by bkang016 on 9/20/17.
 */

public class ProgressFragment extends DialogFragment {

    public final static String TAG = "ProgressFragment";


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public static ProgressFragment newInstance(String ipAddress, String port) {

        return null;
    }
}
