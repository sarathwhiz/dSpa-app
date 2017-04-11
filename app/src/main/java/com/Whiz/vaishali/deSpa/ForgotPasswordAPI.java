package com.Whiz.vaishali.deSpa;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ForgotPasswordAPI {
    @FormUrlEncoded
    @POST("/forgotPassword.php")
    public void passforgot(

            @Field("user_mail") String user_mail,
            Callback<Response> callback);


}
