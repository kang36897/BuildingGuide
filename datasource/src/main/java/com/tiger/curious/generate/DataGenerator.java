package com.tiger.curious.generate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiger.curious.model.Company;
import com.tiger.curious.utils.ExcelUtils;

import java.io.InputStream;
import java.util.List;

public class DataGenerator {

    public static void main(String[] args) {
        InputStream is = DataGenerator.class.getClassLoader().getResourceAsStream("arrangement.xlsx");

        List<Company> companyList = ExcelUtils.readFromXLSX(is);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(companyList);
        System.out.println(json);


    }

}
