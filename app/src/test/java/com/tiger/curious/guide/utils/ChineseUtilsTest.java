package com.tiger.curious.guide.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bkang016 on 5/16/17.
 */
public class ChineseUtilsTest {

    final static String[] INPUT = {"天王盖地虎", "宝塔镇河妖"};
    final static String[] EXPECTED = {"twgdh", "btzhy"};

    @Test
    public void getSpells() throws Exception {
        for (int i = 0; i < INPUT.length; i++) {

            String result = ChineseUtils.getSpells(INPUT[i]);
            System.out.println("result:" + result);

            assertEquals(EXPECTED[i], result);
        }
    }

}