package com.coolweather.app.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtil {
    public static void sendHttpRequest(final String address,
                                       final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    Log.d("TAG", "run:1 "+address);
                    InputStream in = connection.getInputStream();
                    Log.d("TAG", "run: "+in);
                    //InputStream inputStream = connection.getInputStream();
                    Log.d("TAG", "run:2 ");
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    Log.d("TAG", "run:3 ");

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
// 回调onFinish()方法
                        listener.onFinish(response.toString());
                        Log.d("TAG", "run: "+response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
// 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}