package com.example.ranalucky.retrofitexample.services;

/**
 * Created by Rana lucky on 9/7/2016.
 */

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by adinugroho
 */
/* Call<User> editUser (@Header("Authorization") String authorization, @Part("file\"; filename=\"pp.png\" ") RequestBody file , @Part("userName") RequestBody fname, @Part("password") RequestBody id);
*/
public interface MultiPartService {
    @Multipart
    @POST("auth/signup")
    Call<ResponseBody> postImage(/*@Header("Authorization") String authorization,*/
                                 @Part MultipartBody.Part image,
                                 @Part("user[username]") RequestBody userName,
                                 @Part("user[password]") RequestBody password,
                                 @Part("user[old_password]") RequestBody oldPassword,
                                 @Part("user[new_password]") RequestBody newPassword,
                                 @Part("user[password_confirmation]") RequestBody passConfirmation,
                                 @Part("user[device_type]") RequestBody deviceType,
                                 @Part("user[device_uuid]") RequestBody deviceUUId);



}

