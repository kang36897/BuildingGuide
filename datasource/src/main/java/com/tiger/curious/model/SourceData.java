package com.tiger.curious.model;

/**
 * Created by bkang016 on 5/29/17.
 */

public class SourceData {

    public final static String SHEET_DATA_VERSION = "data_version";


    public final static String SHEET_DATA_CONTENT = "data_content";


    private int mVersionNumber;

    public int getVersionNumber() {
        return mVersionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.mVersionNumber = versionNumber;
    }
}
