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
import com.tiger.curious.guide.databinding.FragmentResultBinding;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.vmodel.SearchResultModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkang016 on 5/16/17.
 */

public class ResultFragment extends Fragment {

    public final static String TAG = "ResultFragment";

    public final static String KEY_RESULTS = "key_results";

    private FragmentResultBinding mBinding;
    private SearchResultModel mModel;

    private List<Company> mData;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    public static ResultFragment newInstance(ArrayList<Company> data) {
        ResultFragment fragment = newInstance();

        fragment.setArgs(data);

        return fragment;
    }

    public void setArgs(ArrayList<Company> data) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_RESULTS, data);

        setArguments(args);
    }

    public void updateDataSource(List<Company> data){
        mData = data;
        mModel.setResults(mData);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            mData = getArguments().getParcelableArrayList(KEY_RESULTS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false);

        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mModel = new SearchResultModel((ControlView) getActivity());
        mBinding.setModel(mModel);

        mModel.setResults(mData);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
