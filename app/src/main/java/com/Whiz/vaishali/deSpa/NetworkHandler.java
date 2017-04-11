package com.Whiz.vaishali.deSpa;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkHandler {
    private static final String TAG = NetworkHandler.class.getSimpleName();

    public NetworkHandler() {
    }

//    private NetworkHandler() {
//    }

    public static String doGet(String reqUrl ) {

        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    public static String doPost(String url, String jsonData) {
        String response = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(jsonData);
            wr.flush();
            response = convertStreamToString(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String doPostin(String url, String jsonData) {
        String response = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(jsonData);
            wr.flush();
            response = convertStreamToString(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public  String postjson(String urll, JSONObject jsonData) {
        String response = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(urll).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(7000);
            conn.setReadTimeout(7000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(String.valueOf(jsonData));
            wr.flush();
            response = convertStreamToString(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
