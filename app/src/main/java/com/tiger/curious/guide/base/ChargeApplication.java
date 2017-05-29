package com.tiger.curious.guide.base;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.database.DaoMaster;
import com.tiger.curious.guide.database.DaoSession;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.utils.ChineseUtils;
import com.tiger.curious.guide.utils.JsonUtils;

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


    private DaoSession session;


    @Override
    public void onCreate() {
        super.onCreate();


        initData();

    }

    //TODO reuse the process of loading data
    private void initData() {
        Observable.fromCallable(new Callable<List<Company>>() {
            @Override
            public List<Company> call() throws Exception {
                return JsonUtils.readFrom(getApplicationContext(), R.raw.arrangement);
            }
        }).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Company>>() {
            @Override
            public void accept(@NonNull List<Company> companyList) throws Exception {


                initDatabase(companyList);
            }
        });

    }

    private void initDatabase(@NonNull List<Company> companyList) {
        Database db = new DaoMaster.DevOpenHelper(getApplicationContext(), "building").getWritableDb();

        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();

        session.getCompanyDao().deleteAll();

        for (Company company : companyList) {
            company.setAbbreviation(ChineseUtils.getSpells(company.getGroup() + company.getName()));

            session.getCompanyDao().insert(company);
        }
    }


}
