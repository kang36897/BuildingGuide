package com.tiger.curious.guide.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiger.curious.guide.database.DaoMaster;
import com.tiger.curious.guide.database.DaoSession;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.utils.ChineseUtils;

import org.greenrobot.greendao.database.Database;

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
        String[] data = new String[]{
                "{\"name\":\"田螺(上海)技术有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"101\",\"floor\":1}",
                "{\"name\":\"天喔(上海)技术有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"102\",\"floor\":1}",
                "{\"name\":\"黑陀螺技术有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"103\",\"floor\":1}",
                "{\"name\":\"美女技术有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"104\",\"floor\":1}",

                "{\"name\":\"美好时光有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"201\",\"floor\":2}",
                "{\"name\":\"伊莱克斯有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"202\",\"floor\":2}",
                "{\"name\":\"温碧泉有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"203\",\"floor\":2}",
                "{\"name\":\"天天有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"204\",\"floor\":2}",

                "{\"name\":\"战国有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"301\",\"floor\":3}",
                "{\"name\":\"栈道游戏有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"302\",\"floor\":3}",
                "{\"name\":\"圣雄时代有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"303\",\"floor\":3}",
                "{\"name\":\"柏拉图有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"304\",\"floor\":3}",
        };

        Gson gson = new GsonBuilder().create();

        Database db = new DaoMaster.DevOpenHelper(this, "building").getWritableDb();


        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession session = daoMaster.newSession();

        session.getCompanyDao().deleteAll();

        for (String item : data) {
            Company company = gson.fromJson(item, Company.class);
            company.setAbbreviation(ChineseUtils.getSpells(company.getName()));

            session.getCompanyDao().insert(company);
        }

    }


}
