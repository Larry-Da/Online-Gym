package org.qmbupt.grp105.backend.dblayer;

import com.alibaba.fastjson.JSON;
import org.qmbupt.grp105.backend.model.Customer;
import org.qmbupt.grp105.backend.model.Transaction;
import org.qmbupt.grp105.backend.model.Video;

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

    private DataManager() throws IOException {

        /**
         * Read information which are stored in JSON files.
         */
        customers    = (ArrayList<Customer>)    JSON.parseArray(IO.read("customer.json"),    Customer.class);
        videos       = (ArrayList<Video>)       JSON.parseArray(IO.read("video.json"),       Video.class);
        transactions = (ArrayList<Transaction>) JSON.parseArray(IO.read("transaction.json"), Transaction.class);


    }

    public void commit() throws IOException {
        IO.write("customer.json",    JSON.toJSONString(customers));
        IO.write("video.json",       JSON.toJSONString(videos));
        IO.write("transaction.json", JSON.toJSONString(transactions));
    }


    public static void main(String[] args) throws IOException {
        System.out.println(DataManager.getInstance().transactions.get(0).transactionId);

    }

}
