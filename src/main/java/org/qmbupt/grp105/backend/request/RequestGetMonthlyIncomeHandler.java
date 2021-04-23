package org.qmbupt.grp105.backend.request;

import com.alibaba.fastjson.*;
import org.qmbupt.grp105.backend.dblayer.TransactionManager;

public class RequestGetMonthlyIncomeHandler implements RequestHandler {

    
    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            int month = Integer.parseInt(JSON.parseObject(payload).getString("month"));
            int monthlyTotalIncome = 0;

            monthlyTotalIncome = TransactionManager.getMonthlyIncome(month);

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
