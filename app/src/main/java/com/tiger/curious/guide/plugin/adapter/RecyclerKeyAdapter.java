package com.tiger.curious.guide.plugin.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiger.curious.guide.BR;
import com.tiger.curious.guide.R;
import com.tiger.curious.guide.model.Key;
import com.tiger.curious.guide.vmodel.KeyModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by lulala on 4/6/2017.
 */

public class RecyclerKeyAdapter extends RecyclerView.Adapter<RecyclerKeyAdapter.KeyViewHolder> {

    final static int TYPE_ACTION_VIEW = -1;
    private List<Key> mData = Collections.EMPTY_LIST;

    public RecyclerKeyAdapter(List<Key> data) {
        mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_ACTION_VIEW;
        }

        return super.getItemViewType(position);
    }

    @Override
    public KeyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;

        if (viewType == TYPE_ACTION_VIEW) {
            binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.component_action_in_keyboard, parent, false);
        } else {
            binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.component_button_in_keyboard, parent, false);
        }

        return new KeyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(KeyViewHolder holder, int position) {
        holder.populate(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateDataSource(List<Key> data) {

        mData = data;
        notifyDataSetChanged();
    }

    static class KeyViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;
        private KeyModel mModel;

        public KeyViewHolder(View itemView) {
            super(itemView);

            mModel = new KeyModel();
            mBinding = DataBindingUtil.findBinding(itemView);
            mBinding.setVariable(BR.model, mModel);

        }


        public void populate(Key key) {
            mModel.setKey(key);
            mBinding.executePendingBindings();
        }
    }
}
