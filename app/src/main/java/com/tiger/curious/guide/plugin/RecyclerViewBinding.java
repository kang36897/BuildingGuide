package com.tiger.curious.guide.plugin;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.plugin.adapter.RecyclerCompanyAdapter;

import java.util.List;

/**
 * Created by bkang016 on 5/16/17.
 */

public class RecyclerViewBinding {

    @BindingAdapter("recyclerEntities")
    public static void populateRecyclerView(RecyclerView targetView, List<Company> data) {

        if (targetView.getAdapter() == null) {
            RecyclerCompanyAdapter adapter = new RecyclerCompanyAdapter(data);
            targetView.setLayoutManager(new LinearLayoutManager(targetView.getContext()));

            DividerItemDecoration dividerItemDecoration =
                    new DividerItemDecoration(targetView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(targetView.getResources()
                    .getDrawable(R.drawable.list_divider));
            targetView.addItemDecoration(dividerItemDecoration);

            targetView.setAdapter(adapter);
            return;
        }

        ((RecyclerCompanyAdapter) targetView.getAdapter()).updateDataSource(data);
    }
}
