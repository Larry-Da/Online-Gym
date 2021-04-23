/**
 * This file should be removed in the future.
 */

package org.qmbupt.grp105.backend.request;

import com.alibaba.fastjson.*;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;

public class RequestGetVideoIdsByCusIdHandler implements RequestHandler {

    
    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String cusId = JSON.parseObject(payload).getString("cusId");
            JSONObject responsePayload = new JSONObject();
            responsePayload.put("VideoIds", CustomerManager.getCustomerById(cusId).videosHistory);
            retJson.put("payload", responsePayload);
            retJson.put("status", "success");
        } catch (Exception ex) {
            retJson.put("status", "failed");
            ex.printStackTrace();
        }

        return retJson.toJSONString();
    }

    
}
