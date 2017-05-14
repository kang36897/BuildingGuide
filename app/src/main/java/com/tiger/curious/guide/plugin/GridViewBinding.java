package com.tiger.curious.guide.plugin;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.OnKeyClickedListener;
import com.tiger.curious.guide.model.Key;
import com.tiger.curious.guide.plugin.adapter.SAdapter;

import java.util.List;

/**
 * Created by bkang016 on 5/14/17.
 */

public class GridViewBinding {

    @BindingAdapter("gridEntities")
    public static void populateGridView(GridView targetView, List<Key> data) {
        targetView.setAdapter(new SAdapter(data));
    }


    @BindingAdapter("onItemClickListener")
    public static void attachListener(final GridView targetView, OnKeyClickedListener listener) {

        if (targetView.getTag(R.id.onKeyClickedListener) == null) {
            targetView.setTag(R.id.onKeyClickedListener, listener);
        }


        if (targetView.getOnItemClickListener() == null) {

            targetView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    OnKeyClickedListener listener = (OnKeyClickedListener) parent.getTag(R.id.onKeyClickedListener);

                    listener.onClicked((Key) parent.getItemAtPosition(position));

                }
            });
        }


    }
}
