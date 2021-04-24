package org.qmbupt.grp105.backend.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
import org.qmbupt.grp105.backend.dblayer.TransactionManager;

public class NewRequestHandlerExtendMembership implements RequestHandler {

    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();

        try {
            String cusId = JSON.parseObject(payload).getString("cusId");
            if (!CustomerManager.extendMembership(cusId))
                throw new Exception();

            retJson.put("payload", new JSONObject());
            retJson.put("status", "success");

        } catch (Exception ex) {
            retJson.put("status", "failed");
            ex.printStackTrace();
        }

        return retJson.toJSONString();
    }
}
