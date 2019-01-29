package com.example.mahmudinm.androidlaravellogin.network;

import com.example.mahmudinm.androidlaravellogin.model.User;
import com.example.mahmudinm.androidlaravellogin.network.response.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/auth/login")
    Call<UserResponse> postLogin(@Field("email") String email,
                                 @Field("password") String password);

    @GET("api/auth/me")
    Call<User> getUser(@Header("Authorization") String token);

}
