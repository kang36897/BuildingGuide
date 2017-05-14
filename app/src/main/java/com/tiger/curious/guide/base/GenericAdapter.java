package com.tiger.curious.guide.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by bkang016 on 5/14/17.
 */

public class GenericAdapter<T> extends BaseAdapter {

    protected List<T> mData;

    public GenericAdapter() {
        mData = Collections.EMPTY_LIST;
    }


    public GenericAdapter(List<T> data) {
        mData = data;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
