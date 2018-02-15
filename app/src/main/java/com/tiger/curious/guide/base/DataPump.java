package com.tiger.curious.guide.base;

import android.content.Context;
import android.util.Log;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.database.DaoMaster;
import com.tiger.curious.guide.database.DaoSession;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.utils.ChineseUtils;
import com.tiger.curious.guide.utils.EChineseUtils;
import com.tiger.curious.guide.utils.JsonUtils;

import org.greenrobot.greendao.database.Database;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bkang016 on 9/20/17.
 */

public class DataPump {

    //TODO reuse the process of loading data
    public static void initData(final Context context) {
        Observable.fromCallable(new Callable<List<Company>>() {
            @Override
            public List<Company> call() throws Exception {
                return JsonUtils.readFrom(context, R.raw.arrangement);
            }
        })
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Company>>() {
                    @Override
                    public void accept(@NonNull List<Company> companyList) throws Exception {
                        buildIndexes(companyList);
                        putData(context, companyList);
                    }
                });

    }

    public static void buildIndexes(@NonNull List<Company> companyList) {
        //add indexes for companies
        for (Company item : companyList) {
            Log.d("Company", item.toString());

            String characters = item.getGroup() + item.getName();
            String abbreviation = "";
            abbreviation = EChineseUtils.getSpellCapitalChar(characters);
//            if (characters.equals("上海信麟资产管理有限公司")) {
//                abbreviation = "shxlzcglyxgs";
//            } else if (characters.equals("华鑫国际信托有限公司")) {
//                abbreviation = "hxgjxtyxgs";
//            } else if (characters.equals("上海炫踪网络股份有限公司")) {
//                abbreviation = "shxzwlgfyxgs";
//            } else {
//                abbreviation = ChineseUtils.getSpells(characters);
//            }

            item.setAbbreviation(abbreviation);

        }
    }

    public static void putData(Context context, @NonNull List<Company> companyList) {
        Database db = new DaoMaster.DevOpenHelper(context, "building").getWritableDb();

        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();

        session.getCompanyDao().deleteAll();

        for (Company company : companyList) {
            session.getCompanyDao().insert(company);
        }
    }


}
