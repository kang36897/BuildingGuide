package com.tiger.curious.utils;

import org.junit.Test;

import java.io.InputStream;

/**
 * Created by bkang016 on 5/29/17.
 */
public class ExcelUtilsTest {
    @Test
    public void readFromXLS() throws Exception {

    }

    @Test
    public void readFromXLSX() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("test_data.xlsx");

        ExcelUtils.readFromXLSX(is);
    }

}