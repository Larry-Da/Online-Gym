package org.qmbupt.grp105.backend.request;

import com.alibaba.fastjson.*;

public class RequestGetCusIdsByGenderHandler implements RequestHandler {


    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String gender = JSON.parseObject(payload).getString("gender");
            JSONArray customers = JSON.parseArray(IO.read("customer.json"));
            JSONArray cusIdList = new JSONArray();

            for (int i = 0; i < customers.size(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("gender").equals(gender))
                    cusIdList.add(customer.getString("cusId"));
            }
            
            retJson.put("status", "success");

            JSONObject responsePayload = new JSONObject();
            responsePayload.put("cusIds", cusIdList);
            retJson.put("payload", responsePayload);

        } catch (Exception ex) {
            retJson.put("status", "failed");
        }

        return retJson.toJSONString();
    }
    
}
