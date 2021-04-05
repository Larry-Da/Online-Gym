package request;

import com.alibaba.fastjson.*;

public class RequestGetCusIdByNameHandler implements RequestHandler {

    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String cusName = JSON.parseObject(payload).getString("cusName");
            JSONArray customers = JSON.parseArray(IO.read("customer.json"));
            
            boolean found = false;
            for (int i = 0; i < customers.size(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("name").equals(cusName)) {

                    JSONObject responsePayload = new JSONObject();
                    responsePayload.put("cusId", customer.getString("cusId"));
                    retJson.put("payload", responsePayload);
                    retJson.put("status", "success");
                    found = true;
                    break;
                }
            }
            if (!found)
                retJson.put("status", "failed");

        } catch (Exception ex) {
            retJson.put("status", "failed");
        }

        return retJson.toJSONString();
    }
    
}
