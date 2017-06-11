package com.tiger.curious.generate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiger.curious.model.Company;
import com.tiger.curious.utils.ExcelUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.List;

public class DataGenerator {

    public static void main(String[] args) throws IOException {
        InputStream is = DataGenerator.class.getClassLoader().getResourceAsStream("arrangement.xlsx");

        List<Company> companyList = ExcelUtils.readFromXLSX(is);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(companyList);
        System.out.println(json);


        System.out.println("args=" + args.length);
        if (args != null && args.length > 0) {
            writeToJsonFile(json, args[0]);

        } else {
            String parent = new File(DataGenerator.class.getClassLoader()
                    .getResource("arrangement.xlsx").toString()).getParent();
            writeToJsonFile(json, parent);
        }
    }

    private static void writeToJsonFile(String json, String parent) throws IOException {

        File jsonData = new File(parent, "arrangement.json");

        System.out.println(jsonData.toString());

        jsonData.createNewFile();

        RandomAccessFile randomAccessFile = null;
        try {

            randomAccessFile = new RandomAccessFile(jsonData, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.write(json.getBytes(Charset.forName("UTF-8")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
