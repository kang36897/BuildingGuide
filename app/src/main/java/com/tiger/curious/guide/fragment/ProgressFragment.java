package com.tiger.curious.guide.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

import com.tiger.curious.guide.R;
import com.tiger.curious.guide.base.DataPump;
import com.tiger.curious.guide.model.ArrangementFeedback;
import com.tiger.curious.guide.model.Company;
import com.tiger.curious.guide.service.GuilderService;
import com.tiger.curious.guide.service.ServiceGenerator;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bkang016 on 9/20/17.
 */

public class ProgressFragment extends DialogFragment {

    public final static String TAG = "ProgressFragment";

    final static String REMOTE_SERVER = "remote_server";
    final static String REMOTE_PORT = "remote_port";

    private String mRemoteServer;
    private String mRemotePort;

    public final static PublishSubject<Boolean> DATA_SYNC_STATUS = PublishSubject.create();
    private Disposable mStatusSubscription;
    private Disposable mDisposable;

    public static ProgressFragment newInstance(String ipAddress, String port) {

        ProgressFragment fragment = new ProgressFragment();

        Bundle args = new Bundle();
        args.putString(REMOTE_SERVER, ipAddress);
        args.putString(REMOTE_PORT, port);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRemoteServer = getArguments().getString(REMOTE_SERVER);
        mRemotePort = getArguments().getString(REMOTE_PORT);


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setIndeterminate(true);
        dialog.setMessage(getString(R.string.title_sync_data));

        return dialog;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mStatusSubscription = DATA_SYNC_STATUS.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
                        dismiss();
                        if (aBoolean) {
                            Toast.makeText(getActivity(), R.string.hint_sync_data_success, Toast.LENGTH_LONG).show();
                            return;
                        }

                        Toast.makeText(getActivity(), R.string.hint_sync_data_failed, Toast.LENGTH_LONG).show();

                    }
                });

        ServiceGenerator.changeBaseUrl("http://" + mRemoteServer + ":" + mRemotePort + "/");
        GuilderService service = ServiceGenerator.createService(GuilderService.class);
        service.fetchArrangement().enqueue(new Callback<ArrangementFeedback>() {
            @Override
            public void onResponse(Call<ArrangementFeedback> call, Response<ArrangementFeedback> response) {
                ArrangementFeedback feedback = response.body();
                if (feedback.getError() != null) {
                    DATA_SYNC_STATUS.onNext(false);
                    return;
                }


                mDisposable = Observable.fromArray(feedback.getData()).delay(300, TimeUnit.MILLISECONDS)
                        .observeOn(Schedulers.io())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<List<Company>>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull List<Company> companies) throws Exception {
                                if (companies == null || companies.isEmpty()) {
                                    DATA_SYNC_STATUS.onNext(false);
                                    return;
                                }

                                DataPump.buildIndexes(companies);
                                DataPump.putData(getActivity().getApplicationContext(), companies);
                                DATA_SYNC_STATUS.onNext(true);

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                                Log.d(TAG, "fetch_data", throwable);
                                DATA_SYNC_STATUS.onNext(false);
                            }
                        });


            }

            @Override
            public void onFailure(Call<ArrangementFeedback> call, Throwable throwable) {
                DATA_SYNC_STATUS.onNext(false);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }

        if (mStatusSubscription != null && !mStatusSubscription.isDisposed()) {
            mStatusSubscription.dispose();
        }
    }
}
