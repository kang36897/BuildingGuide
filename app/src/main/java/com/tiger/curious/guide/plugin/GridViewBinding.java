package com.tiger.curious.guide.plugin;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
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
    final static String TAG = "GridViewBinding";


    @BindingAdapter("gridEntities")
    public static void populateGridView(GridView targetView, List<Key> data) {

        if (targetView.getAdapter() == null) {
            targetView.setAdapter(new SAdapter(data));
            return;
        }

        ((SAdapter) targetView.getAdapter()).updateDataSource(data);

    }


    @BindingAdapter("itemClickListener")
    public static void attachListener(final GridView targetView, OnKeyClickedListener listener) {

        if (targetView.getTag(R.id.onKeyClickedListener) == null) {
            targetView.setTag(R.id.onKeyClickedListener, listener);
        }

    }
}
