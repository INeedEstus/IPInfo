package com.example.ipinfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface  {

    @GET("geo")
    Call<IPInfo> getIPInfo();
}
