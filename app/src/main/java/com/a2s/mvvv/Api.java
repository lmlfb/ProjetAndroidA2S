package com.a2s.mvvv;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://api-apk-learn-sql.fr/";

    @GET("get_all_question.php")
    Call<List<Enonce>> getAllEnonce();

    @GET("get_all_level.php")
    Call<List<Level>> getAllLevel();
}