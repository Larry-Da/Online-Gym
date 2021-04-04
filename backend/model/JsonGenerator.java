package model;

import com.alibaba.fastjson.*;

public class JsonGenerator {
    public static void main(String[] args) {
        JSONObject object = JSON.parseObject("{\"request\":\"userinfo\"}");
    }
}
