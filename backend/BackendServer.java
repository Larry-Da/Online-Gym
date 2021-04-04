import java.io.*;

import com.alibaba.fastjson.*;

import request.RequestHandler;
import request.RequestHandlerFactory;

//yyyy-MM-dd
public class BackendServer {
    public static String execute(String clientRequestStr) {
        try {
            JSONObject object = JSON.parseObject(clientRequestStr);

            RequestHandler rh = RequestHandlerFactory.createHandler(object.getString("request"));

            return rh.execute(object.getJSONObject("payload").toJSONString());
        } catch (Exception ex) {
            return "{\"status\": \"failed\"}";
        }
    }
    

    private static void init() {
        // if both info.json.tmp and info.json occur
        //   delete info.json
        //   return
        // if only info.json.tmp
        //   mv info.json.tmp to info.json
        //   return

    }
}