package com.itislevel.lyl.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/27.17:00
 * path:com.itislevel.lyl.utils.GsonUtil
 **/
public class GsonUtil {
    /**
     * Json解析器
     */
    private static final JsonParser PARSER = new JsonParser();

    /**
     * Gson 单例
     */
    private static Gson sGson = null;



    /**
     * 设置一个通用的Gson
     *
     * @param gson
     */
    public static void setGson(Gson gson) {
        if (sGson != gson) {
            sGson = gson;
        }
    }

    /**
     * 获取通用GSON
     *
     * @return
     */
    public static Gson getGson() {
        if (sGson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
            Gson gson = builder.create();
            sGson = gson;
        }
        return sGson;
    }

    /**
     * 将对象序列化成json 字符串
     * @param object
     * @return
     */
    public static String obj2JSON(Object object){
       return getGson().toJson(object);
    }

    /**
     * 获取Json对象里面的键, 并将其值以String返回.
     * 当值为json对象或者json数组时,返回其json格式的字符串
     *
     * @param json
     * @param key
     * @return
     */
    public static String getString(String json, String key) {
        JsonElement element;
        try {
            element = PARSER.parse(json);
        } catch (Exception e) {
            // This is Auto-generated catch block
            log(json, e);
            return null;
        }
        if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            JsonElement value = obj.get(key);
            if (value != null) {
                if (value instanceof JsonPrimitive) {
                    return value.getAsString();
                } else if (value instanceof JsonNull) {
                    return null;
                } else {
                    return value.toString();
                }
            }
        }
        return null;
    }

    /**
     * 将json解析为一个JsonObject
     *
     * @param json
     * @return
     */
    public static JsonObject toJsonObject(String json) {
        try {
            JsonElement element = PARSER.parse(json);
            return element.getAsJsonObject();
        } catch (Exception e) {
            // This is Auto-generated catch block
            log(json, e);
            return null;
        }
    }

    /**
     * 将json解析为一个JsonArray
     *
     * @param json
     * @return
     */
    public static JsonArray toJsonArray(String json) {
        try {
            JsonElement element = PARSER.parse(json);
            return element.getAsJsonArray();
        } catch (Exception e) {
            // This is Auto-generated catch block
            log(json, e);
            return null;
        }
    }

    /**
     * 将json对象序列化为一个对象实例
     *
     * @param json
     * @param clss 要序列化为的对象
     * @return
     */
    public static <T> T toObject(String json, Class<T> clss) {
        Gson gson = getGson();
        try {
            return gson.fromJson(json, clss);
        } catch (Exception e) {
            // This is Auto-generated catch block
            log(json, e);
            return null;
        }
    }

    /**
     * 将json对象序列化为一个对象实例
     *
     * @param json
     * @param type 要序列化为的对象, 主要用于带范型的类
     * @return
     */
    public static <T> T toObject(String json, Type type) {
        Gson gson = getGson();
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            // This is Auto-generated catch block
            log(json, e);
            return null;
        }
    }

    /**
     * 将json对象序列化为一个对象实例
     *
     * @param json
     * @param clss 要序列化为的对象
     * @return
     */
    public static <T> T toObject(JsonElement json, Class<T> clss) {
        Gson gson = getGson();
        try {
            return gson.fromJson(json, clss);
        } catch (Exception e) {
            // This is Auto-generated catch block
            log("json: " + json, e);
            return null;
        }
    }

    /**
     * 将json对象序列化为一个对象实例
     *
     * @param json
     * @param type 要序列化为的对象, 主要用于带范型的类
     * @return
     */
    public static <T> T toObject(JsonElement json, Type type) {
        Gson gson = getGson();
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            // This is Auto-generated catch block
            log("json: " + json, e);
            return null;
        }
    }

    /**
     * 将json数组序列化为一个对象的List实例
     *
     * @param json
     * @param clss 对象类型
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> clss) {
        JsonElement element;
        try {
            element = PARSER.parse(json);
        } catch (Exception e) {
            // This is Auto-generated catch block
            log(json, e);
            return null;
        }
        if (element.isJsonArray()) {
            Gson gson = getGson();
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            ArrayList<T> result = new ArrayList<T>(size);
            for (int i = 0; i < size; i++) {
                JsonElement item = array.get(i);
                T t;
                try {
                    t = gson.fromJson(item, clss);
                } catch (Exception e) {
                    // This is Auto-generated catch block
                    log(item.toString(), e);
                    continue;
                }
                result.add(t);
            }
            return result;
        }
        return null;
    }

    /**
     * 将json数组序列化为一个对象的List实例
     *
     * @param json
     * @param type 对象类型
     * @return
     */
    public static <T> List<T> toList(String json, Type type) {
        JsonElement element;
        try {
            element = PARSER.parse(json);
        } catch (Exception e) {
            // This is Auto-generated catch block
            log(json, e);
            return null;
        }
        if (element.isJsonArray()) {
            Gson gson = getGson();
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            ArrayList<T> result = new ArrayList<T>(size);
            for (int i = 0; i < size; i++) {
                JsonElement item = array.get(i);
                T t;
                try {
                    t = gson.fromJson(item, type);
                } catch (Exception e) {
                    // This is Auto-generated catch block
                    log(item.toString(), e);
                    continue;
                }
                result.add(t);
            }
            return result;
        }
        return null;
    }

    /**
     * 将json数组序列化为一个对象的List实例
     *
     * @param json
     * @param clss 对象类型
     * @return
     */
    public static <T> List<T> toList(JsonElement json, Class<T> clss) {
        final JsonElement element = json;
        if (json == null) {
            log("json: " + json, new NullPointerException("json is null"));
            return null;
        }
        if (element.isJsonArray()) {
            Gson gson = getGson();
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            ArrayList<T> result = new ArrayList<T>(size);
            for (int i = 0; i < size; i++) {
                JsonElement item = array.get(i);
                T t;
                try {
                    t = gson.fromJson(item, clss);
                } catch (Exception e) {
                    // This is Auto-generated catch block
                    log(item.toString(), e);
                    continue;
                }
                result.add(t);
            }
            return result;
        }
        return null;
    }

    /**
     * 将json数组序列化为一个对象的List实例
     *
     * @param json
     * @param type 对象类型
     * @return
     */
    public static <T> List<T> toList(JsonElement json, Type type) {
        final JsonElement element = json;
        if (json == null) {
            log("json: " + json, new NullPointerException("json is null"));
            return null;
        }
        if (element.isJsonArray()) {
            Gson gson = getGson();
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            ArrayList<T> result = new ArrayList<T>(size);
            for (int i = 0; i < size; i++) {
                JsonElement item = array.get(i);
                T t;
                try {
                    t = gson.fromJson(item, type);
                } catch (Exception e) {
                    // This is Auto-generated catch block
                    log(item.toString(), e);
                    continue;
                }
                result.add(t);
            }
            return result;
        }
        return null;
    }

    /**
     * 写日志
     *
     * @param json
     * @param e
     */
    private static void log(String json, Exception e) {

    }
}
