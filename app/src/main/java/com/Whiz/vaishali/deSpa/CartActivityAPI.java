package com.Whiz.vaishali.deSpa;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface CartActivityAPI {
    @FormUrlEncoded
    @POST("/deSpa_Appointment.php")
    public void insertUser(
            @Field("cust_lv") String cust_lv,
            @Field("total") String total,

            Callback<Response> callback);

}
