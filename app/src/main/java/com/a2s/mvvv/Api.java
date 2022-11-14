package com.a2s.mvvv;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://api-apk-learn-sql.fr/";

    @GET("get_all_question.php")
    Call<List<Enonce>> getAllEnonce();

    @GET("get_one_question_by_id.php")
    Call<List<Enonce>> getSelectedLvlById(@Query("id") int selectedExoLvl);

    @GET("get_question_by_level.php")
    Call<List<Enonce>> getSelectedLvl(@Query("lvl") int selectedExoLvl);

    @GET("get_all_level.php")
    Call<List<Level>> getAllLevel();

    @GET("get_all_car.php")
    Call<List<voiture>> getCars();
}