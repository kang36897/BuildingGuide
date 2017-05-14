package com.tiger.curious.guide.plugin;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.GenericAdapter;
import com.tiger.curious.guide.base.OnKeyClickedListener;
import com.tiger.curious.guide.model.Key;
import com.tiger.curious.guide.plugin.adapter.SAdapter;

import java.util.List;

/**
 * Created by bkang016 on 5/14/17.
 */

public class ListViewBinding {

    @BindingAdapter("listEntities")
    public static void populateListView(ListView targetView, List<Key> data) {

        targetView.setAdapter(new SAdapter(data));
    }


}
