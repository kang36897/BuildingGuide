package com.tiger.curious.guide.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.tiger.curious.guide.R;

/**
 * Created by bkang016 on 9/20/17.
 */

public class ProgressFragment extends DialogFragment {

    public final static String TAG = "ProgressFragment";

    final static String REMOTE_SERVER = "remote_server";
    final static String REMOTE_PORT = "remote_port";

    private String mRemoteServer;
    private String mRemotePort;

    public static ProgressFragment newInstance(String ipAddress, String port) {

        ProgressFragment fragment = new ProgressFragment();

        Bundle args = new Bundle();
        args.putString(REMOTE_SERVER, ipAddress);
        args.putString(REMOTE_PORT, port);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRemoteServer = getArguments().getString(REMOTE_SERVER);
        mRemotePort = getArguments().getString(REMOTE_PORT);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(true);
        dialog.setMessage(getString(R.string.title_sync_data));

        return dialog;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
