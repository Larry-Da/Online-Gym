package request;

import com.alibaba.fastjson.*;

public class RequestGetMonthlyIncomeHandler implements RequestHandler {

    
    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {

            int month = Integer.parseInt(JSON.parseObject(payload).getString("month"));
            JSONArray transactions = JSON.parseObject(IO.read("gym.json")).getJSONArray("transactions");
            int monthlyTotalIncome = 0;

            for (int i = 0; i < transactions.size(); i++) {
                JSONObject transaction = transactions.getJSONObject(i);
                if (Integer.parseInt(transaction.getString("date").substring(5, 7)) == month) {
                    monthlyTotalIncome += transaction.getInteger("mount");
                }
            }


            JSONObject responsePayload = new JSONObject();
            responsePayload.put("income", "" + monthlyTotalIncome);
            retJson.put("payload", responsePayload);
            retJson.put("status", "success");

        } catch (Exception ex) {
            retJson.put("status", "failed");
            ex.printStackTrace();
        }

        return retJson.toJSONString();
    }
    
}
