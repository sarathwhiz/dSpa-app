package com.Whiz.vaishali.deSpa;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface FeedbackAPI {
    @FormUrlEncoded
    @POST("/deSpa_Feedback.php")
    public void insertcomments(
            @Field("name") String name,
            @Field("contact") String contact ,
            @Field("email") String email,
            @Field("comments") String comments,
            @Field("rating") String rating,
            Callback<Response> callback);
}
