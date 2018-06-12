package com.duckduckgogogo.utils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JSONHandler {
    static JSONObject object;

    //验证操作是否成功
    public static boolean isSuccess(String json){
        object = JSONObject.fromObject(json);

        try {
            String result = object.getString("result");

            if (result.equals("success")) {
                object = null;

                return true;
            } else {
                object = null;

                return false;
            }
        }catch (JSONException e){//进行查询操作时的判断
            if(object.getString("updateDate") != null){
                return true;
            } else {
                return false;
            }
        }
    }

    public static String getElement_(String json, String key){
        object = JSONObject.fromObject(json);

        return object.getString(key);
    }

    //获取json中所有与key值对应的value
    public static String[] getElements(String json, String... keys){
        int size = keys.length;

        String[] elements = new String[size];
        object = JSONObject.fromObject(json);

        for(int i = 0; i < size; i++){
            elements[i] = object.getString(keys[i]);
        }

        return elements;
    }
}
