package com.Whiz.vaishali.deSpa.webservice;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebService {

    OkHttpClient client;
//       client.setConnectTimeout(15, TimeUnit.SECONDS);
//       client.setReadTimeout(Defaults.READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public WebService() {
        if (client == null) {
            client = new OkHttpClient();
             client.setConnectTimeout(2000, TimeUnit.SECONDS);
            client.setReadTimeout(2000, TimeUnit.SECONDS);
        }
    }

//    public String postRequest(String url, JSONArray formBody, RequestBody requestBody) throws IOException {
//        String result = null;
//        try {
//            RequestBody body= RequestBody.create(JSON,formBody.toString());
//            Request request = new Request.Builder()
//                    .addHeader("Content-Type","application/json")
//                    .url(url)
//                    .post(body)
//                    .post(requestBody)
//                    .build();
//            Response response = client.newCall(request).execute();
//            result = response.body().string();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public String postRequest(String url, JSONObject formBody) throws IOException {
        String result=null;                                                           //  String result= String.valueOf(formBody);
        try {
            RequestBody body= RequestBody.create(JSON,formBody.toString());
            Request request = new Request.Builder()
                    .addHeader("Content-Type","application/json")
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  String post(String url, JSONObject json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String GetRequest(String url) throws IOException {
        String result = null;
        try {
//
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}