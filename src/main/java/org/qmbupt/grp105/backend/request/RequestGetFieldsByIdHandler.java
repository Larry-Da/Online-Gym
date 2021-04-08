package org.qmbupt.grp105.backend.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.*;

import org.qmbupt.grp105.backend.model.Customer;
import org.qmbupt.grp105.backend.model.Video;


public class RequestGetFieldsByIdHandler implements RequestHandler {

    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String type = JSON.parseObject(payload).getString("type");
            String id = JSON.parseObject(payload).getString("id");
            String idName = "";
            String jsonFileName = "";
            Class class1 = Object.class;

            ArrayList<String> fields = new ArrayList(Arrays.asList(JSON.parseObject(payload).getJSONArray("fields").toArray()));

            if (type.equals("Customer")) {
                class1 = Customer.class;
                jsonFileName = "customer.json";
                idName = "cusId";
            }
            else if (type.equals("Video")) {
                class1 = Video.class;
                jsonFileName = "video.json";
                idName = "videoId";
            }
            
            Map<String, Object> mapRet = getFieldsById(idName, id, jsonFileName, fields, class1);

            //JSONObject responsePayload = new JSONObject();
            //responsePayload.put("cusId", customer.getString("cusId"));
            retJson.put("payload", mapRet);
            retJson.put("status", "success");
            

        } catch (Exception ex) {
            retJson.put("status", "failed");
            ex.printStackTrace();
        }

        return retJson.toJSONString();
    }


    public static Map<String, Object>
    getFieldsById(String idName, 
                  String id, 
                  String jsonFileName, 
                  ArrayList<String> fields, 
                  Class class1) throws Exception {

        Map<String, Object> ret = new HashMap<>();
        
        try {
            JSONArray objects = JSON.parseArray(IO.read(jsonFileName));
            
            boolean found = false;

            for (int i = 0; i < objects.size(); i++) {

                Object object = objects.getJSONObject(i).toJavaObject(class1);
                
                if (object.getClass().getDeclaredField(idName).get(object).equals(id)) {

                    for (int j = 0; j < fields.size(); j++) {
                        String field = fields.get(j);
                        ret.put(field, object.getClass().getDeclaredField(field).get(object));
                    }

                    found = true;
                    break;

                }
            }
            if (!found) {
                throw new Exception();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }

        return ret;

    }

    public static void main(String[] args) throws Exception {
        RequestHandler rh = new RequestGetFieldsByIdHandler();
        String ret2 = rh.execute("{\"type\": \"Customer\",\"id\": \"Cs15\",\"fields\": [\"name\", \"videosHistory\", \"dateOfBirth\"]}");
        System.out.println(ret2);
    }
}
