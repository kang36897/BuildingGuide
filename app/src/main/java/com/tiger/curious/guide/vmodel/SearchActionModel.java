package com.tiger.curious.guide.vmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.tiger.curious.guide.base.OnKeyClickedListener;
import com.tiger.curious.guide.model.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bkang016 on 5/14/17.
 */

public class SearchActionModel extends BaseObservable implements OnKeyClickedListener {

    public static List<Key> NUMBER_KEYS = new ArrayList<>();
    public static List<Key> ALPHABET_KEYS = new ArrayList<>();

    public static String[] NUMBER = new String[]{
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9"
    };

    public static String[] ALPHABET = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
    };

    static {
        for (String item : NUMBER) {
            NUMBER_KEYS.add(Key.getNumber(item));
        }

        for (String item : ALPHABET) {
            ALPHABET_KEYS.add(Key.getLetter(item));
        }


    }


    private int searchType;

    private String inputContent;

    private Context mContext;

    public SearchActionModel() {

    }


    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
        notifyChange();
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
        notifyChange();
    }


    private StringBuilder mCachedString;


    @Override
    public void onClicked(Key key) {

    }
}
