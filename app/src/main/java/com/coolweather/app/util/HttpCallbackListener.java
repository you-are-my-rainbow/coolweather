package com.coolweather.app.util;

public abstract class HttpCallbackListener {
    public abstract void onFinish(String response);
    public abstract void onError(Exception e);
}
