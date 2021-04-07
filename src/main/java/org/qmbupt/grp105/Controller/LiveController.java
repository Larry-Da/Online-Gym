package org.qmbupt.grp105.Controller;
import org.qmbupt.grp105.Entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class LiveController {
    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;
    public LiveController() {}

    public ArrayList<LiveSession> getAllLiveSessions() {
        param.put("type","LiveSession");
        param.put("fields",new String[]{"liveSessionId"});
        ArrayList<LiveSession> liveSessions = new ArrayList<>();
        request = new Request("getFieldsById",param);
//       response = new Response(backend.getLiveSessionIds(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"liveSessionIds\":[\"L001\",\"L002\"]}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("liveSessionIds");
            List<String> liveSessionIds = gson.fromJson(jsonArray, List.class);
            for(String liveSessionId : liveSessionIds) {
                LiveSession liveSession = getLiveSessionBySessionId(liveSessionId);
                liveSessions.add(liveSession);
            }
            param.clear();
            return liveSessions;
        }
        param.clear();
        return null;
    }
    public LiveSession getLiveSessionBySessionId(String liveSessionId) {
        param.put("type","LiveSession");
        param.put("fields",LiveSession.getAllAttributes());
        request = new Request("getFieldsById", param);
        //       response = new Response(backend.getLiveSessionId(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"liveSessionId\":\"L001\",\"url\":\"usr/local/bin\"," +
                "\"rating\":7.8,\"category\":\"Yoga\",\"startTime\":\"2020-05-01\",\"endTime\":\"2020-07-02\",\"likes\":100," +
                "\"viewCounts\":3000,\"Customer_cusId\":\"C001\",\"Coach_coachId\":\"Ch001\",\"availableNum\":100}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            LiveSession liveSession = gson.fromJson(response.getPayload(), LiveSession.class);
            param.clear();
            return liveSession;
        }
        param.clear();
        return null;
    }
    public ArrayList<LiveSession> getLiveSessoinsByCategory(String category) {
        ArrayList<LiveSession> liveSessions = getAllLiveSessions();
        ArrayList<LiveSession> sessionList = new ArrayList<>();
        for(LiveSession liveSession : liveSessions) {
            if(liveSession.getCategory().equalsIgnoreCase(category)) {
                sessionList.add(liveSession);
            }
        }
        return sessionList;
    }
    public int getAvailableNumOfSession(String liveSessionId) {
        LiveSession liveSession = getLiveSessionBySessionId(liveSessionId);
        return liveSession.getAvailableNum();
    }
    public boolean bookLiveSession(String cusId, String liveSessionId) {
        param.put("cusId",cusId);
        param.put("liveSessionId",liveSessionId);
        request = new Request("bookLiveSession",param);
//        response = new Response(backend.bookLiveSession(request));
        response = new Response("{\"status\":\"success\"}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            param.clear();
            return true;
        }
        param.clear();
        return false;
     }
     public static void main(String[] args) {
        LiveController liveController = new LiveController();
         LiveSession liveSession = liveController.getLiveSessionBySessionId("L001");
         System.out.println(liveSession.toString());
     }
 }
