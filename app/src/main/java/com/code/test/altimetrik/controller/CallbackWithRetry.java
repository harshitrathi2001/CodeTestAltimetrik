package com.code.test.altimetrik.controller;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class CallbackWithRetry<T> implements Callback<T> {

    private final int TOTAL_RETRIES = 2;
    private static final String TAG = CallbackWithRetry.class.getSimpleName();
    private final Call<T> call;
    private int retryCount = 0;

    private AsyncCompleteListener completeListener;

    public CallbackWithRetry(Call<T> call, AsyncCompleteListener completeListener) {
        this.call = call;
        this.completeListener = completeListener;
    }

    @Override
    public void onFailure(Call<T>call, Throwable t) {

        if (retryCount++ < TOTAL_RETRIES) {
            retry();
        }
        else {
            if (completeListener == null)
                return;

            completeListener.onTaskComplete(null);
        }
    }

    private void retry() {
        call.clone().enqueue(this);
    }
}