package request;

import com.alibaba.fastjson.*;

public class RequestGetCustomerByIdHandler implements RequestHandler {

    
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

                    responsePayload.put("cusId", customer.getString("cusId"));
                    responsePayload.put("age", "" + utils.getAgeByBirthday(customer.getString("dateOfBirth")));
                    responsePayload.put("name", customer.getString("name"));
                    responsePayload.put("password", customer.getString("password"));
                    responsePayload.put("phoneNo", customer.getString("phoneNo"));
                    responsePayload.put("email", customer.getString("email"));
                    responsePayload.put("gender", customer.getString("gender"));
                    responsePayload.put("dateOfBirth", customer.getString("dateOfBirth"));
                    responsePayload.put("membershipLevel", "L" + customer.getString("membershipLevel"));
                    if (customer.getString("expireDate") != null)
                        responsePayload.put("remainTime", "" + utils.getRemainDays(customer.getString("expireDate")));
                    else
                        responsePayload.put("remainTime", "" + 0);
                    responsePayload.put("balance", "" + customer.getIntValue("balance"));
                    responsePayload.put("points", "" + customer.getIntValue("xp"));
                    
                    
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
