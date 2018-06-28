package com.duckduckgogogo.utils;

import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JSONHandler {
    static JSONObject object;
    static JSONArray array;

    /**
     * 验证操作是否成功
     * */
    public static boolean isSuccess(String json){
        try {//JSONObject
            object = JSONObject.fromObject(json);

            String result = object.getString("result");

            if (result.equals("success")) {
                object = null;

                return true;
            } else {
                object = null;

                return false;
            }

        }catch (JSONException e){//JSONArray
            try {
                array = JSONArray.fromObject(json);

                if (array.size() != 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (JSONException e1){//search or update operation
                try {
                    if (object.getString("updateDate") != null) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (JSONException e2){
                    return object.size() > 3 ? true : false;
                }
            }

        } catch (Exception e){//other error
            return false;
        }
    }

    public static String getElement_(String json, String key){
        try {

            object = JSONObject.fromObject(json);

            return object.getString(key);
        } catch (Exception e){
            return "";
        }
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

    //可以优化
    public static JSONArray getJsonArryFromResponse(String json, String... removeKeys){
        try{
            JSONArray jsonArray = JSONArray.fromObject(json);

            for(int i = 0; i < jsonArray.size(); i++) {
                for(String key : removeKeys) {
                    jsonArray.getJSONObject(i).remove(key);
                }
            }

            return jsonArray;
        } catch(Exception e){
            return new JSONArray();
        }
    }

    public static int getJsonArrayLength(String json){
        try {
            array = JSONArray.fromObject(json);

            return array.size();
        } catch (Exception e){
            return 0;
        }
    }

    public static JSONObject getIndeedJsonObjectFromJson(String json){
        JSONObject object = JSONObject.fromObject(json);

        object.remove("server");

        return object;
    }
}
