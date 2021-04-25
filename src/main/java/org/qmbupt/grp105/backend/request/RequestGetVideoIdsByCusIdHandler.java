package org.qmbupt.grp105.backend.request;

import com.alibaba.fastjson.*;

public class RequestGetVideoIdsByCusIdHandler implements RequestHandler {

    
    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String cusId = JSON.parseObject(payload).getString("cusId");
            JSONArray customers = JSON.parseArray(IO.read("customer.json"));
            
            for (int i = 0; i < customers.size(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("cusId").equals(cusId)) {

                    JSONObject responsePayload = new JSONObject();

                    responsePayload.put("VideoIds",customer.getJSONArray("videosHistory"));
                    
                    retJson.put("payload", responsePayload);

                    retJson.put("status", "success");
                }
            }

        } catch (Exception ex) {
            retJson.put("status", "failed");
            ex.printStackTrace();
        }

        return retJson.toJSONString();
    }

    
}
