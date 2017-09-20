package com.tiger.curious.guide.fragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.databinding.FragmentUpdatePanelBinding;
import com.tiger.curious.guide.vmodel.DataSyncModel;

/**
 * Created by bkang016 on 9/20/17.
 */

public class UpdatePanelFragment extends DialogFragment {


    private FragmentUpdatePanelBinding mBinding;
    private DataSyncModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_update_panel, null, false);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(mBinding.getRoot());


        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mModel = new DataSyncModel(this);
        mBinding.setModel(mModel);




    }
}
