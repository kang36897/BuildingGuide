package com.tiger.curious.guide.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.BaseActivity;
import com.tiger.curious.guide.contract.ControlView;
import com.tiger.curious.guide.fragment.ResultFragment;
import com.tiger.curious.guide.fragment.SearchFragment;
import com.tiger.curious.guide.model.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulala on 11/5/2017.
 */

public class MainActivity extends BaseActivity implements ControlView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.right_panel, new SearchFragment(), SearchFragment.TAG)
                    .commit();

        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.result_container, new ResultFragment(), ResultFragment.TAG)
                .commit();
    }

    @Override
    public void showSearchResult(List<Company> companyList) {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag(ResultFragment.TAG);

        ResultFragment leftPanel = null;
        if (fragment == null) {
            leftPanel = ResultFragment.newInstance((ArrayList<Company>) companyList);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.result_container, leftPanel, ResultFragment.TAG)
                    .commit();
        } else {
            leftPanel = (ResultFragment) fragment;
            leftPanel.updateDataSource(companyList);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(leftPanel)
                    .commit();
        }


    }
}
