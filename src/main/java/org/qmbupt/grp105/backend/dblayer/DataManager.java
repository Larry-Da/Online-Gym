package org.qmbupt.grp105.backend.dblayer;

import com.alibaba.fastjson.JSON;
import org.qmbupt.grp105.backend.model.*;

import java.io.IOException;
import java.util.ArrayList;

public class DataManager {
    private static DataManager instance = null;

    public static DataManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ArrayList<Customer>    customers;
    public ArrayList<Video>       videos;
    public ArrayList<Transaction> transactions;
    public ArrayList<Session>     sessions;
    public ArrayList<Coach>       coaches;

    private DataManager() throws IOException {

        /**
         * Read information which are stored in JSON files.
         */
        customers    = (ArrayList<Customer>)    JSON.parseArray(IO.read("customer.json"),    Customer.class);
        videos       = (ArrayList<Video>)       JSON.parseArray(IO.read("video.json"),       Video.class);
        transactions = (ArrayList<Transaction>) JSON.parseArray(IO.read("transaction.json"), Transaction.class);
        sessions     = (ArrayList<Session>)     JSON.parseArray(IO.read("sessions.json"),    Session.class);
        coaches      = (ArrayList<Coach>)       JSON.parseArray(IO.read("coaches.json"),     Coach.class);
    }

    public void commit() throws IOException {
        IO.write("customer.json",    JSON.toJSONString(customers));
        IO.write("video.json",       JSON.toJSONString(videos));
        IO.write("transaction.json", JSON.toJSONString(transactions));
        IO.write("sessions.json",    JSON.toJSONString(sessions));
        IO.write("coaches.json",     JSON.toJSONString(coaches));
    }

}
