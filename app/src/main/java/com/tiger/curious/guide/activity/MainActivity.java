package com.tiger.curious.guide.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.BaseActivity;
import com.tiger.curious.guide.contract.ControlView;
import com.tiger.curious.guide.databinding.ActivityMainBinding;
import com.tiger.curious.guide.fragment.ResultFragment;
import com.tiger.curious.guide.fragment.SearchFragment;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.vmodel.TimeAreaModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lulala on 11/5/2017.
 */

public class MainActivity extends BaseActivity implements ControlView {

    private static final long DURATION = 1000 * 60;
    private static final long HIDDEN_DURATION = DURATION * 3;
    private static final long FULLSCREEN_CHECK = HIDDEN_DURATION;

    private ActivityMainBinding mBinding;

    private TimeAreaModel mTimeModel;

    private final Calendar mCalendar = Calendar.getInstance();
    private final Runnable mTimeUpdateTicket = new Runnable() {
        @Override
        public void run() {
            mTimeModel.update(mCalendar.getTime());

            mSuperHandler.postDelayed(this, DURATION);
        }
    };

    private final Runnable mHiddenResultPanel = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentByTag(ResultFragment.TAG);

            if (fragment == null) {
                return;
            }

            ResultFragment leftPanel = (ResultFragment) fragment;
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(leftPanel)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    };

    private final Runnable mEnterImmersiveMode = new Runnable() {
        @Override
        public void run() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE);
            }

            mSuperHandler.postDelayed(mEnterImmersiveMode, FULLSCREEN_CHECK);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mTimeModel = new TimeAreaModel();
        mBinding.setModel(mTimeModel);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.right_panel, new SearchFragment(), SearchFragment.TAG)
                    .commit();

        }

        mSuperHandler.post(mEnterImmersiveMode);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mSuperHandler.removeCallbacks(mHiddenResultPanel);
        mSuperHandler.postDelayed(mHiddenResultPanel, HIDDEN_DURATION);

        return super.dispatchTouchEvent(ev);
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
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            leftPanel = (ResultFragment) fragment;
            leftPanel.updateDataSource(companyList);

            getSupportFragmentManager()
                    .beginTransaction()
                    .show(leftPanel)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        keepTimeUpdate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeTimeUpdate();
    }

    private void keepTimeUpdate() {
        mSuperHandler.post(mTimeUpdateTicket);
    }

    private void removeTimeUpdate() {
        mSuperHandler.removeCallbacks(mTimeUpdateTicket);
    }
}
