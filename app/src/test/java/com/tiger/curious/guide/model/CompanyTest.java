package com.tiger.curious.guide.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by lulala on 11/5/2017.
 */
public class CompanyTest {

    static Gson mGson;

    @BeforeClass
    public static void setUp() {
        mGson = new GsonBuilder().create();
    }


    @Test
    public void testGenerateJson() {
        Company temp = new Company();

        temp.setName("普华永道(上海)技术有限公司");
        temp.setEnglishName("Pwc Us Ltd. Shanghai SDC");
        temp.setFloor(1);
        temp.setRoomNumber("101");

        System.out.println(mGson.toJson(temp));
    }

}