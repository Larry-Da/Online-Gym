package org.qmbupt.grp105.backend.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.qmbupt.grp105.backend.model.Customer;

public class RequestGetCustomerInfoByFieldHandler implements RequestHandler {

    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            
            //Map<String, Object> retMap = GetFieldsByIdHandler.getFieldsById("cusId", id, "customer.json", fields, Customer.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            retJson.put("status", "failed");
        }

        return retJson.toJSONString();
    }
    
    public static void main(String[] args) {
        RequestHandler rh = new RequestGetCustomerInfoByFieldHandler();

        System.out.println(rh.execute("{\"cusId\": \"Cs15\",\"fields\": [\"name\", \"videosHistory\"]}"));
        System.out.println(rh.execute("{\"cusId\": \"Cs15\",\"fields\": [\"videosHistory\"]}"));
    }
}
