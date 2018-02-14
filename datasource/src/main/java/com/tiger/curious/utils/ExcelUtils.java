package com.tiger.curious.utils;


import com.tiger.curious.model.Company;
import com.tiger.curious.model.SourceData;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by bkang016 on 5/26/17.
 */

public class ExcelUtils {

    public static List<Company> readFromXLS(InputStream excelStream) {
        try {

            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(excelStream);
            return getCompanies(poifsFileSystem);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (excelStream != null) {
                try {
                    excelStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;

    }

    private static List<Company> getCompanies(POIFSFileSystem poifsFileSystem) throws IOException {

        ArrayList<Company> container = null;
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);

        HSSFSheet hssfSheet = hssfWorkbook.getSheet(SourceData.SHEET_DATA_CONTENT);
        Iterator<Row> rowIterator = hssfSheet.rowIterator();

        container = new ArrayList<>();

        populate(container, rowIterator);

        return container;
    }


    public static List<Company> readFromXLSX(InputStream excelStream) {

        try {

            OPCPackage pkg = OPCPackage.open(excelStream);
            return getCompanies(pkg);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (excelStream != null) {
                try {
                    excelStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;

    }

    private static List<Company> getCompanies(OPCPackage pkg) throws IOException {
        ArrayList<Company> container = null;

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(pkg);

        XSSFSheet xssfSheet = xssfWorkbook.getSheet(SourceData.SHEET_DATA_CONTENT);
        Iterator<Row> rowIterator = xssfSheet.rowIterator();

        container = new ArrayList<>();

        populate(container, rowIterator);

        return container;
    }

    private static void populate(ArrayList<Company> container, Iterator<Row> rowIterator) {
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            if (row.getRowNum() == 0) {
                continue;
            }

            Company company = new Company();
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();


                switch (cell.getColumnIndex()) {
                    case 0:
                        company.setFloor(extract(cell, Integer.class));
                        break;

                    case 1:
                        company.setRoom(extract(cell, String.class));
                        break;

                    case 2:
                        company.setCompany_name(extract(cell, String.class));
                        break;

                    case 3:
                        company.setGroup_name(extract(cell, String.class));
                        break;

                    case 4:
                        company.setEnglishName(extract(cell, String.class));
                        break;
                }

            }

            container.add(company);
        }
    }

    private static <T> T extract(Cell cell, Class<T> expected) {
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {

            if (expected == Double.class || expected == Float.class) {
                return expected.cast(cell.getNumericCellValue());
            }

            if (expected == Integer.class)
                return expected.cast((int) cell.getNumericCellValue());

            if (expected == String.class) {
                String temp = String.valueOf(cell.getNumericCellValue());
                return expected.cast(temp.endsWith(".0") ? String.valueOf((int) cell.getNumericCellValue()) : temp);
            }
        } else {
            return expected.cast(cell.getStringCellValue().trim());
        }

        return null;
    }

}
