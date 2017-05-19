package com.tiger.curious.guide.contract;

import com.tiger.curious.guide.model.Company;

import java.util.List;

/**
 * Created by bkang016 on 5/17/17.
 */

public interface ControlView {

    public void showSearchResult(List<Company> companyList);
}
