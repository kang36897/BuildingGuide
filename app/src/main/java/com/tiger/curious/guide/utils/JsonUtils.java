package com.tiger.curious.guide.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiger.curious.guide.model.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkang016 on 5/29/17.
 */

public class JsonUtils {
    public static List<Company> readFrom(Context applicationContext, int arrangement) {

        ArrayList<Company> container = new ArrayList<>();

        String[] data = new String[]{
                "{\"name\":\"上海通华燃气轮机服务有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"1702\",\"floor\":17, \"group\":\"中国华电集团公司\"}",
                "{\"name\":\"上海华滨投资有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"1701\",\"floor\":17, \"group\":\"中国华电集团公司\"}",

                "{\"name\":\"华鑫国际信托有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"1601\",\"floor\":16, \"group\":\"中国华电集团公司\"}",
                "{\"name\":\"华远星海运有限公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"1602\",\"floor\":16, \"group\":\"中国华电集团公司\"}",

                "{\"name\":\"华电重工股份有限公司上海分公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"1501\",\"floor\":15, \"group\":\"中国华电集团公司\"}",

                "{\"name\":\"上海公司\",\"englishName\":\"Pwc Us Ltd. Shanghai SDC\",\"roomNumber\":\"2501\",\"floor\":25, \"group\":\"中国华电集团公司\"}",

        };

        Gson gson = new GsonBuilder().create();


        for (String item : data) {
            Company company = gson.fromJson(item, Company.class);
            company.setAbbreviation(ChineseUtils.getSpells(company.getGroup() + company.getName()));

            container.add(company);
        }

        return container;
    }
}
