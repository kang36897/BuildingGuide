package com.tiger.curious.guide.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.icu.lang.UProperty;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.BaseActivity;
import com.tiger.curious.guide.contract.ControlView;
import com.tiger.curious.guide.databinding.ActivityMainBinding;
import com.tiger.curious.guide.fragment.ResultFragment;
import com.tiger.curious.guide.fragment.SearchFragment;
import com.tiger.curious.guide.fragment.UpdatePanelFragment;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.vmodel.TimeAreaModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lulala on 11/5/2017.
 */

public class MainActivity extends BaseActivity implements ControlView {

    private static final long DURATION = 1000 * 60;
    private static final long HIDDEN_DURATION = DURATION * 3;
    private static final long FULLSCREEN_CHECK = HIDDEN_DURATION;

    private static final long VALID_INTERVAL = 1000;
    private long mOriginal = Long.MIN_VALUE;
    private int mCount = 0;

    private ActivityMainBinding mBinding;

    private TimeAreaModel mTimeModel;

    private final Runnable mTimeUpdateTicket = new Runnable() {
        @Override
        public void run() {
            mTimeModel.update(new Date());

            mSuperHandler.postDelayed(this, DURATION);
        }
    };

    private final Runnable mHiddenResultPanel = new Runnable() {
        @Override
        public void run() {
            hideResultFragment();

            hideSearchKeyboard();


        }
    };

    private void hideSearchKeyboard() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SearchFragment.TAG);

        if (fragment == null) {
            return;
        }

        SearchFragment rightPanel = (SearchFragment) fragment;
        rightPanel.hideKeyboard();
    }

    private void hideResultFragment() {
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

            mOriginal = Long.MIN_VALUE;
            mCount = 0;

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


        mBinding.hiddenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCount > 2) {
                    return;
                }

                if (mOriginal != Long.MIN_VALUE) {

                    if (mOriginal + VALID_INTERVAL < System.currentTimeMillis()) {
                        mOriginal = Long.MIN_VALUE;
                        mCount = 0;
                        return;
                    }

                }

                mCount++;

                Toast.makeText(MainActivity.this, "click -> " + mCount, Toast.LENGTH_SHORT).show();
                if (mCount == 3) {
                    //exit the full screen  mode

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        getWindow().getDecorView().setSystemUiVisibility(
                                View.SYSTEM_UI_FLAG_VISIBLE);
                    }


                    Intent settingsIntent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                    startActivity(settingsIntent);

                    mSuperHandler.removeCallbacks(mEnterImmersiveMode);
                    mSuperHandler.postDelayed(mEnterImmersiveMode, FULLSCREEN_CHECK);
                }

            }
        });

        mBinding.syncBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                UpdatePanelFragment.newInstance().show(getSupportFragmentManager(), UpdatePanelFragment.TAG);

                return true;
            }
        });

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
    public void hideSearchResult() {
        hideResultFragment();
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
