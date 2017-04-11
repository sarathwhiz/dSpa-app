package com.Whiz.vaishali.deSpa;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface SignUpAPI {
    @FormUrlEncoded
    @POST("/deSpa_Signup.php")
    public void insertUser(
            @Field("user_name") String user_name,
            @Field("user_contact") String user_contact,
            @Field("user_mail") String user_mail,
            @Field("user_password") String user_password,

            Callback<retrofit.client.Response> callback);
}
