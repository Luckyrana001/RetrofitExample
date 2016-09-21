package com.example.ranalucky.retrofitexample.services;

import com.example.ranalucky.retrofitexample.model.ResponseModel;
import com.example.ranalucky.retrofitexample.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Rana lucky on 9/15/2016.
 */
public interface ApiArrayInterface {



    //@FormUrlEncoded
    @GET("api/RetrofitAndroidArrayResponse")
    Call<List<Student>> getStudentDetails();



}
