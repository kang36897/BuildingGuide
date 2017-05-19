package com.tiger.curious.guide.vmodel;

import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.OnKeyClickedListener;
import com.tiger.curious.guide.model.Key;

/**
 * Created by bkang016 on 5/14/17.
 */

public class KeyModel extends BaseObservable {
    final static String TAG = "KeyModel";

    private Key key;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
        notifyChange();
    }

    public void onClick(View v) {
        Log.d(TAG, key.toString());

        Object obj = ((View) v.getParent()).getTag(R.id.onKeyClickedListener);

        if (obj == null) {
            return;
        }


        ((OnKeyClickedListener) obj).onClicked(key);

    }
}
