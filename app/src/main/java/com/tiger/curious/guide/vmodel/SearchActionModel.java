package com.tiger.curious.guide.vmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.tiger.curious.guide.base.OnKeyClickedListener;
import com.tiger.curious.guide.base.Releasable;
import com.tiger.curious.guide.contract.ControlView;
import com.tiger.curious.guide.database.CompanyDao;
import com.tiger.curious.guide.database.DaoMaster;
import com.tiger.curious.guide.database.DaoSession;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.model.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bkang016 on 5/14/17.
 */

public class SearchActionModel extends BaseObservable implements OnKeyClickedListener, Releasable {

    final static String TAG = "SearchActionModel";

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

        ALPHABET_KEYS.add(Key.getAction(Key.TYPE_DELETE_ACTION));
    }

    final static int TYPE_SEARCH_BY_UNKNOWN = -1;
    final static int TYPE_SEARCH_BY_FLOOR = 0;
    final static int TYPE_SEARCH_BY_COMPANY = 1;
    final static int TYPE_MODIFY_SEARCH_ACTION = 2;

    private int searchType = TYPE_SEARCH_BY_UNKNOWN;

    private String inputContent;

    public SearchActionModel() {

    }

    private ControlView mControlView;

    public SearchActionModel(ControlView view) {
        mControlView = view;
    }


    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
        notifyChange();
    }

    public List<Key> getNumberKeys() {
        return SearchActionModel.NUMBER_KEYS;
    }

    private StringBuilder mCachedString = new StringBuilder();


    private int visibilityOfKeyboard = View.GONE;

    public int getVisibilityOfKeyboard() {
        return visibilityOfKeyboard;
    }

    public void setVisibilityOfKeyboard(int visibilityOfKeyboard) {
        this.visibilityOfKeyboard = visibilityOfKeyboard;
        notifyChange();
    }

    @Override
    public void onClicked(Key key) {
        Log.d(TAG, key.toString());

        int updatedSearchType = decideSearchPlanBy(key);

        if (searchType == TYPE_SEARCH_BY_UNKNOWN) {

            if (updatedSearchType == TYPE_MODIFY_SEARCH_ACTION) {
                return;
            }


            searchType = updatedSearchType;
            mCachedString.append(key.getValue());

            setInputContent(mCachedString.toString());
            return;
        }


        if (updatedSearchType == TYPE_MODIFY_SEARCH_ACTION) {

            mCachedString.deleteCharAt(mCachedString.length() - 1);

            if (mCachedString.length() == 0) {
                searchType = TYPE_SEARCH_BY_UNKNOWN;
            }

            setInputContent(mCachedString.toString());
            return;
        }


        if (updatedSearchType != searchType) {

            searchType = updatedSearchType;
            //delete the cached string
            mCachedString.delete(0, mCachedString.length());
            mCachedString.append(key.getValue());

            setInputContent(mCachedString.toString());
            return;
        }


        mCachedString.append(key.getValue());
        setInputContent(mCachedString.toString());
    }


    public void performDeleteOperation() {
        onClicked(Key.getAction(Key.TYPE_DELETE_ACTION));
    }

    private int decideSearchPlanBy(Key key) {
        if (key.getType() == Key.TYPE_NUMBER) {

            return TYPE_SEARCH_BY_FLOOR;
        } else if (key.getType() == Key.TYPE_ALPHABET) {

            return TYPE_SEARCH_BY_COMPANY;
        } else {

            return TYPE_MODIFY_SEARCH_ACTION;
        }
    }


    @BindingAdapter("input")
    public static void updateSearchInput(EditText targetView, String content) {
        targetView.setText(content);

        if (TextUtils.isEmpty(content)) {
            return;
        }
        targetView.setSelection(content.length());
    }


    private Disposable mSearchActionSubscription;

    public void onSearch(final View targetView) {

        onDestroy();

        mSearchActionSubscription = Observable.<List<Company>>create(new ObservableOnSubscribe<List<Company>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Company>> e) throws Exception {

                if (searchType == TYPE_SEARCH_BY_UNKNOWN || searchType == TYPE_MODIFY_SEARCH_ACTION) {
                    return;
                }


                DaoSession session = DaoMaster.newDevSession(targetView.getContext(), "building");
                CompanyDao dao = session.getCompanyDao();

                if (searchType == TYPE_SEARCH_BY_FLOOR) {
                    List<Company> companyList = dao.queryBuilder()
                            .where(CompanyDao.Properties.Floor.eq(inputContent)).list();

                    e.onNext(companyList);

                } else if (searchType == TYPE_SEARCH_BY_COMPANY) {
                    List<Company> companyList = dao.queryBuilder()
                            .where(CompanyDao.Properties.Abbreviation.like("%" + inputContent + "%"))
                            .list();

                    e.onNext(companyList);
                }
            }
        }).debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Company>>() {
                    @Override
                    public void accept(@NonNull List<Company> companies) throws Exception {
                        mControlView.showSearchResult(companies);
                    }
                });


    }

    @Override
    public void onDestroy() {
        if (mSearchActionSubscription != null && !mSearchActionSubscription.isDisposed()) {
            mSearchActionSubscription.dispose();
        }
    }
}
