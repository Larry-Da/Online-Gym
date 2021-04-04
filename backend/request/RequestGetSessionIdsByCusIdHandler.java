package request;

import com.alibaba.fastjson.*;

public class RequestGetSessionIdsByCusIdHandler implements RequestHandler {
    
    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String cusId = JSON.parseObject(payload).getString("cusId");
            JSONArray customers = JSON.parseArray(IO.read("customer.json"));
            
            for (int i = 0; i < customers.size(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("cusId").equals(cusId)) {
                    retJson.put("status", "success");
                    JSONObject responsePayload = new JSONObject();
                    responsePayload.put("sessionIds", customer.getJSONArray("sessionIds"));
                    retJson.put("payload", responsePayload);
                }
            }

        } catch (Exception ex) {
            retJson.put("status", "failed");
        }

        return retJson.toJSONString();
    }
    
}
