package org.qmbupt.grp105.Controller;
import org.qmbupt.grp105.Entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
import org.qmbupt.grp105.backend.dblayer.SessionManager;
import org.qmbupt.grp105.backend.model.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class LiveController {
    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;
    private static LiveController liveController = new LiveController();
    private LiveController() {}
    public static LiveController getController(){
        return liveController;
    }
    public ArrayList<LiveSession> getLiveSessionByCusId(String cusId) {
        ArrayList<LiveSession> res = new ArrayList<>();
        try {
            Customer customer = CustomerManager.getCustomerById(cusId).converter();
            for(String s: customer.getBookedSessions()) {
                res.add(SessionManager.getSessionById(s).converter());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ArrayList<LiveSession> getLiveSessionByCoachId(String coId)
    {
        ArrayList<LiveSession> sessions = getAllLiveSessions();
        ArrayList<LiveSession> res = new ArrayList<>();
        for(LiveSession i : sessions)
        {
            if(i.getCoach_coachId().equals(coId))
            {
                res.add(i);
            }
        }
        return res;
    }
    public ArrayList<LiveSession> getAllLiveSessions() {
        ArrayList<Session> sessions = null;
        ArrayList<LiveSession> res = new ArrayList<>();
        try {
            sessions = SessionManager.getAllSessions();
            for(Session s: sessions) {
                res.add(s.converter());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    public LiveSession getLiveSessionBySessionId(String liveSessionId) {
        LiveSession liveSession = null;
        try {
            liveSession = SessionManager.getSessionById(liveSessionId).converter();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return liveSession;
    }
    public ArrayList<LiveSession> getSessionsByCoach(String keyword) {
        ArrayList<LiveSession> liveSessions = getAllLiveSessions();
        return liveSessions;
//        ArrayList<LiveSession> res = new ArrayList<>();
//        for(LiveSession s: liveSessions) {
//            if(s.getCoach_coachId().equals("co3")) {
//                res.add(s);
//            }
//        }
//        return res;
    }
    public ArrayList<LiveSession> filterSessionByCategory(ArrayList<LiveSession> sessions, List<String> category) {
        ArrayList<LiveSession> sessions1 = null;
        ArrayList<LiveSession> res = new ArrayList<>();

        if(sessions == null) {
            sessions1 = getAllLiveSessions();
        }
        else {
            sessions1 = sessions;
        }
        if(category.size() == 0)
            return sessions1;
        for(LiveSession s: sessions1) {
            if(category.contains(s.getCategory())) {
                res.add(s);
            }
        }
        return res;
    }
    public int getAvailableNumOfSession(String liveSessionId) {
        LiveSession liveSession = getLiveSessionBySessionId(liveSessionId);
        return liveSession.getAvailableNum();
    }
    public void addSession(LiveSession liveSession) {
        try {
            SessionManager.addSession(liveSession.converter());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}