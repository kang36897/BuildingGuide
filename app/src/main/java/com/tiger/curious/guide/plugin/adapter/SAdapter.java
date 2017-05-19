package com.tiger.curious.guide.plugin.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.GenericAdapter;
import com.tiger.curious.guide.databinding.ComponentButtonInKeyboardBinding;
import com.tiger.curious.guide.model.Key;
import com.tiger.curious.guide.vmodel.KeyModel;

import java.util.List;

/**
 * Created by bkang016 on 5/14/17.
 */

public class SAdapter extends GenericAdapter<Key> {

    public SAdapter(List<Key> data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VHolder holder = null;
        if (convertView == null) {

            holder = new VHolder(parent);
            convertView = holder.getView();
            convertView.setTag(holder);
        } else {

            holder = (VHolder) convertView.getTag();
        }

        holder.populate(mData.get(position));

        return convertView;
    }

    public void updateDataSource(List<Key> data) {
        mData = data;
        notifyDataSetChanged();
    }

    static class VHolder {
        private ComponentButtonInKeyboardBinding mBinding;
        private KeyModel mModel;

        public VHolder(ViewGroup parent) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.component_button_in_keyboard,
                    parent, false);

            mModel = new KeyModel();

            mBinding.setModel(mModel);


        }

        public View getView() {
            return mBinding.getRoot();
        }


        public void populate(Key data) {
            mModel.setKey(data);
            mBinding.executePendingBindings();
        }
    }
}
