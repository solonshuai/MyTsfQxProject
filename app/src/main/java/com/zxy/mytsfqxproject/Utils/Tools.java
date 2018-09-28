package com.zxy.mytsfqxproject.Utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Tools {
    public static RequestBody getRequestBody(HashMap<String, Object> hashMap) {
        StringBuilder data = new StringBuilder();
        if (hashMap != null && hashMap.size() > 0) {
            for (Object o : hashMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object key = entry.getKey();
                Object val = entry.getValue();
                data.append(key).append("=").append(val).append("&");
            }
        }
        String jso = data.substring(0, data.length() - 1);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), jso);
        return requestBody;
    }

    public static RequestBody getRequestBodys(HashMap<String, Object> hashMap) {
        String route = new Gson().toJson(hashMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        return body;
    }
}
