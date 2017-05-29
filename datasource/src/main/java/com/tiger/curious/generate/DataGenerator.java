package com.tiger.curious.generate;

import com.tiger.curious.utils.ExcelUtils;

import java.io.InputStream;

public class DataGenerator {

    public static void main(String[] args) {
        InputStream is = DataGenerator.class.getClassLoader().getResourceAsStream("arrangement.xlsx");

        ExcelUtils.readFromXLSX(is);
    }

}
