package com.tiger.curious.guide.utils;

import android.content.Context;

import com.tiger.curious.guide.model.Company;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bkang016 on 5/26/17.
 */

public class ExcelUtils {


    public static List<Company> readFrom(InputStream excelStream) {

        try {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(excelStream);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);

            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            Iterator rowIterator = hssfSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();

                Iterator cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    HSSFCell cell = (HSSFCell) cellIterator.next();
                    System.out.print(cell.getStringCellValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }


    public static List<Company> readFrom(Context context, int raw) {
        return readFrom(context.getResources().openRawResource(raw));
    }
}
