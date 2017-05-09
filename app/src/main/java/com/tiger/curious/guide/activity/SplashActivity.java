package com.tiger.curious.guide.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.BaseActivity;
import com.tiger.curious.guide.databinding.ActivitySplashBinding;

/**
 * Created by lulala on 9/5/2017.
 */

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

    }
}
