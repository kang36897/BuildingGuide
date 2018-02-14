package com.tiger.curious.guide.vmodel;

import android.databinding.BaseObservable;
import android.text.Html;

import com.tiger.curious.guide.model.Company;

/**
 * Created by bkang016 on 5/16/17.
 */

public class ItemResultModel extends BaseObservable {

    private Company company;

    public ItemResultModel(Company data) {
        company = data;
    }

    public ItemResultModel() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
        notifyChange();
    }

    public String computeFormalRoomNumber() {
        if (company.getRoomNumber().equalsIgnoreCase("whole")) {
            return company.getFloor() + "L";
        } else {
            if (company.getRoomNumber().contains(".")) {
                return company.getRoomNumber().replaceAll("\\.", ".\n");

            } else
                return company.getRoomNumber();
        }
    }
}
