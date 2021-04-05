package request;

import com.alibaba.fastjson.*;

public class RequestGetCusNumByLevelHandler implements RequestHandler {

    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String membershipLevel = JSON.parseObject(payload).getString("membershipLevel");
            JSONArray customers = JSON.parseArray(IO.read("customer.json"));
            
            int count = 0;
            for (int i = 0; i < customers.size(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (membershipLevel.equals("L" + customer.getIntValue("membershipLevel"))) {
                    count++;
                }
            }

            retJson.put("status", "success");

            JSONObject responsePayload = new JSONObject();
            responsePayload.put("num", "" + count);
            retJson.put("payload", responsePayload);

        } catch (Exception ex) {
            retJson.put("status", "failed");
        }

        return retJson.toJSONString();
    }
    
}
