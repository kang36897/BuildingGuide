package com.tiger.curious.guide.vmodel;

import android.databinding.BaseObservable;

import com.tiger.curious.guide.model.Key;

/**
 * Created by bkang016 on 5/14/17.
 */

public class KeyModel extends BaseObservable {
    private Key key;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
        notifyChange();
    }

    public void onClick() {

    }
}
