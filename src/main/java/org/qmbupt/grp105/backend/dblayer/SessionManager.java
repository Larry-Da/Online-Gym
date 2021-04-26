package org.qmbupt.grp105.backend.dblayer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.qmbupt.grp105.backend.model.Customer;
import org.qmbupt.grp105.backend.model.Session;

import java.io.*;
import java.util.ArrayList;

public class SessionManager {

    /**
     * get a MUTABLE reference of a Session specified by sessionId
     * @param sessionId
     * @return reference of that session
     * @throws IOException
     */
    public static Session getSessionById(String sessionId) throws IOException {
        ArrayList<Session> sessions = DataManager.getInstance().sessions;
        for (Session session : sessions) {
            if (session.sessionId.equals(sessionId)) {
                return session;
            }
        }
        return null;
    }

    /**
     * add a session with auto-generated sessionId
     * @param session
     * @throws IOException
     */
    public static void addSession(Session session) throws IOException {
        session.sessionId = getNextId();
        DataManager.getInstance().sessions.add(session);
        DataManager.getInstance().commit();
    }

    /**
     * get all sessionIds of sessions
     * @return array of sessionIds
     * @throws IOException
     */
    public static ArrayList<String> getSessionIds() throws IOException {
        ArrayList<String> ret = new ArrayList<>();
        ArrayList<Session> sessions = DataManager.getInstance().sessions;
        for (Session session : sessions) {
            ret.add(session.sessionId);
        }
        return ret;
    }

    /**
     * get CusIds who booked thhe specified session
     * @param sessionId
     * @return array of CusId
     * @throws IOException
     */
    public static ArrayList<String> getCusIdsBySessionId(String sessionId) throws IOException {
        ArrayList<Customer> customers = DataManager.getInstance().customers;
        ArrayList<String> ret = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.bookedSessions.contains(sessionId)) {
                ret.add(customer.cusId);
            }
        }
        return ret;
    }


    /**
     * get all sessions
     * @return
     * @throws IOException
     */
    public static ArrayList<Session> getAllSessions() throws IOException {
        return DataManager.getInstance().sessions;
    }







    private static String getNextId() throws IOException {
        ArrayList<String> ids = getSessionIds();
        int max = 0;
        for (String id : ids) {
            int val = Integer.parseInt(id.substring(3));
            if (val > max) max = val;
        }
        return "lvs" + (max + 1);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getAllSessions());
    }
}
