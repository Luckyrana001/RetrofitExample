package com.example.ranalucky.retrofitexample.services;

import com.example.ranalucky.retrofitexample.model.ResponseModel;
import com.example.ranalucky.retrofitexample.model.Student;
import com.example.ranalucky.retrofitexample.utils.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiObjectInterface {
    /*@GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/
    @FormUrlEncoded
    @POST("log_location")
    Call<ResponseModel> updateLocation(@Field("user[current_lat]") String lat,
                                       @Field("user_id") String user,
                                       @Field("user[current_long]") String longitude);

    @GET("api/RetrofitAndroidObjectResponse")
    Call<Student> getStudentDetails();
/*

    @GET("train_schedules/movements")
    Call<MovementsResponseModel> updateLocation(@Header("Authorization") String authorization);
*/




}
