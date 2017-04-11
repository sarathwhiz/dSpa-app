package com.Whiz.vaishali.deSpa;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface BookingAPI {
    @FormUrlEncoded
    @POST("/deSpa_Appointment.php")
    public void insertUser(
            @Field("user") String username,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("req_date") String req_date,
            @Field("req_time") String req_time,
            @Field("total") String booking_total,


            Callback<Response> callback);
}
