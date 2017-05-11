package com.tiger.curious.guide.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.BaseActivity;

/**
 * Created by lulala on 11/5/2017.
 */

public class SearchActivity extends BaseActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_search);
    }
}
