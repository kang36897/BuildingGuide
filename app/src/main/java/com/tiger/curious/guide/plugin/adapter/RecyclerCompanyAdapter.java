package com.tiger.curious.guide.plugin.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.databinding.ComponentCompanyAndFloorInfoBinding;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.vmodel.ItemResultModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by bkang016 on 5/16/17.
 */

public class RecyclerCompanyAdapter extends RecyclerView.Adapter<RecyclerCompanyAdapter.ItemResultViewHolder> {

    private List<Company> mData;

    public RecyclerCompanyAdapter() {
        mData = Collections.EMPTY_LIST;
    }

    public RecyclerCompanyAdapter(List<Company> input) {
        if (input == null) {
            mData = Collections.EMPTY_LIST;
        } else {
            mData = input;
        }
    }


    public void updateDataSource(List<Company> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ItemResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.component_company_and_floor_info, parent, false).getRoot();

        return new ItemResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemResultViewHolder holder, int position) {

        holder.populate(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ItemResultViewHolder extends RecyclerView.ViewHolder {

        private ComponentCompanyAndFloorInfoBinding mBinding;
        private ItemResultModel mModel;

        public ItemResultViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.findBinding(itemView);

            mModel = new ItemResultModel();
            mBinding.setModel(mModel);
        }

        public void populate(Company value) {
            mModel.setCompany(value);
            mBinding.executePendingBindings();
        }
    }
}
