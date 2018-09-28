package com.zxy.mytsfqxproject.db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Admin on 2017/7/7.
 * 数据缓存类
 */

public class SPUtil {
    private static SharedPreferences preferences;
    private static SPUtil prefenceUtils;
    private static SharedPreferences.Editor editor;

    private static Gson gson = new Gson();

    static Boolean debug;

    public static SPUtil getInstance(Context context) {
        if (prefenceUtils == null) {
            prefenceUtils = new SPUtil(context);
        }
        return prefenceUtils;
    }

    @SuppressLint("CommitPrefEdits")
    private SPUtil(Context context) {
        preferences = context.getSharedPreferences("tsf", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void clearData() {
        editor.clear();
        editor.commit();
    }

    public static void putData(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static void putData(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putData(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putData(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                editor.putString(entry.getKey(), entry.getValue());
                editor.apply();
            }
        }
    }

    public static <K extends Enum> void updateSetting(Context context, K key, Object value) {
        updateSetting(context, getSharedPreferences(context, key), key, value);
    }


    private static <K extends Enum> SharedPreferences getSharedPreferences(Context context, K key) {
        return context.getSharedPreferences(key.getClass().getName(), Activity.MODE_PRIVATE);
    }

    private static <K extends Enum> void updateSetting(Context context, SharedPreferences preferences, K key, @Nullable Object value) {
        SharedPreferences.Editor edit = preferences.edit();
        String keyName = key.name();
        if (value != null) {
            try {
                edit.putString(keyName, gson.toJson(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            edit.remove(keyName);
        }
        edit.apply();
    }

    @Nullable
    public static <K extends Enum> String getSetting(Context context, K key) {
        return getSetting(context, getSharedPreferences(context, key), key, String.class, null);
    }

    @Nullable
    public static <K extends Enum, V> V getSetting(Context context, K key, Class<V> dataClass) {
        return getSetting(context, getSharedPreferences(context, key), key, dataClass, null);
    }

    public static <K extends Enum, V> V getSetting(Context context, K key, Class<V> dataClass, @Nullable V defValue) {
        return getSetting(context, getSharedPreferences(context, key), key, dataClass, defValue);
    }

    private static <K extends Enum, V> V getSetting(Context context, SharedPreferences sharedPreferences, K key, Class<V> dataClass, @Nullable V defValue) {
        String value = sharedPreferences.getString(key.name(), null);
        if (value == null) {
            return defValue;
        } else {
            try {
                return gson.fromJson(value, dataClass);
            } catch (Exception e) {
                return defValue;
            }
        }
    }

    public static <K extends Enum> void clearSettings(final Context context, K key) {
        SharedPreferences preferences = getSharedPreferences(context, key);
        preferences.edit().clear().apply();
    }

    public static Object getData(String key, Object defValue) {
        String valueType;
        if (defValue == null || "null".equals(defValue) || "".equals(defValue)) {
            valueType = "String";
        } else {
            valueType = defValue.getClass().getSimpleName();
        }
        if ("Integer".equals(valueType)) {
            return preferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(valueType)) {
            return preferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(valueType)) {
            return preferences.getString(key, (String) defValue);
        } else if ("Float".equals(valueType)) {
            return preferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(valueType)) {
            return preferences.getLong(key, (Long) defValue);
        }
        return null;
    }

}
