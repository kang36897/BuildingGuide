package com.tiger.curious.guide.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.contract.ControlView;
import com.tiger.curious.guide.databinding.FragmentSearchBinding;
import com.tiger.curious.guide.vmodel.SearchActionModel;

/**
 * Created by bkang016 on 5/14/17.
 */

public class SearchFragment extends Fragment {

    public final static String TAG = "SearchFragment";

    private FragmentSearchBinding mBinding;

    private SearchActionModel mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mModel = new SearchActionModel((ControlView) getActivity());
        mBinding.setModel(mModel);

    }
}
