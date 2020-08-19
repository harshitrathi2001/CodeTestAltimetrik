package com.code.test.altimetrik.controller;

import com.code.test.altimetrik.model.AlbumData;
import com.code.test.altimetrik.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(Constants.SEARCH)
    Call<AlbumData> getAlbumData(@Query("term") String term);

}
