package com.tiger.curious.guide.plugin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tiger.curious.guide.model.Key;

import java.util.List;

/**
 * Created by lulala on 4/6/2017.
 */

public class RecyclerKeyAdapter extends RecyclerView.Adapter {

    private List<Key> mData;

    public RecyclerKeyAdapter(List<Key> data) {
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void updateDataSource(List<Key> data) {

        mData = data;
        notifyDataSetChanged();
    }
}
