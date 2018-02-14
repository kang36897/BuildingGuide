package com.tiger.curious.guide.vmodel;

import android.databinding.BaseObservable;

import com.tiger.curious.guide.base.Releasable;
import com.tiger.curious.guide.contract.ControlView;
import com.tiger.curious.guide.model.Company;

import java.util.List;

/**
 * Created by bkang016 on 5/16/17.
 */

public class SearchResultModel extends BaseObservable implements Releasable {


    private ControlView mControlView;

    private List<Company> results;

    public SearchResultModel(ControlView view) {
        this.mControlView = view;
    }

    public List<Company> getResults() {
        return results;
    }

    public void setResults(List<Company> results) {
        this.results = results;
        notifyChange();
    }


    public void onClose() {
        mControlView.hideSearchResult();
    }

    @Override
    public void onDestroy() {

    }
}
