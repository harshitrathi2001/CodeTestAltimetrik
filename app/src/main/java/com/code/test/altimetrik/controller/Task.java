package com.code.test.altimetrik.controller;

import com.code.test.altimetrik.model.AlbumData;

import retrofit2.Call;
import retrofit2.Response;

public class Task {

    private AsyncCompleteListener completeListener;

    private ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    public Task(AsyncCompleteListener completeListener) {
        this.completeListener = completeListener;
    }

    public void getAlbumData(String term) {
        Call<AlbumData> call = apiService.getAlbumData(term);
        call.enqueue(new CallbackWithRetry<AlbumData>(call, completeListener) {
            @Override
            public void onResponse(Call<AlbumData> call, Response<AlbumData> response) {

                AlbumData data = response.body();

                if(data == null){
                    return;
                }

                if(data.getResultCount() == 0) {
                    return;
                }

                completeListener.onTaskComplete(data);

            }
        });

    }

}
