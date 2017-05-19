package com.tiger.curious.guide.vmodel;

import android.databinding.BaseObservable;

import com.tiger.curious.guide.model.Company;

/**
 * Created by bkang016 on 5/16/17.
 */

public class ItemResultModel extends BaseObservable {

    private Company company;

    public ItemResultModel(Company data) {
        company = data;
    }

    public ItemResultModel(){}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
        notifyChange();
    }
}
