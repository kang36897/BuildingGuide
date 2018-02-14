package com.tiger.curious.guide.base;

import android.content.Context;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.database.DaoMaster;
import com.tiger.curious.guide.database.DaoSession;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.utils.ChineseUtils;
import com.tiger.curious.guide.utils.JsonUtils;
import com.tiger.curious.guide.utils.PreferenceUtils;

import org.greenrobot.greendao.database.Database;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bkang016 on 5/17/17.
 */

public class ChargeApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceUtils.init(getApplicationContext());

        DataPump.initData(getApplicationContext());
    }


}
