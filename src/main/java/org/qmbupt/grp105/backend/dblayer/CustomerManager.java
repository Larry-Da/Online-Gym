package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.alibaba.fastjson.*;

import org.qmbupt.grp105.backend.model.Customer;
import org.qmbupt.grp105.backend.model.Transaction;

public class CustomerManager {

    public static int increaseBalance(String cusId, int addition) throws IOException {

        if (addition <= 0) {
            return 0;
        }

        Customer customer = getCustomerById(cusId);

        customer.balance += addition;

        DataManager.getInstance().commit();

        return addition;
    }

    public static int decreaseBalance(String cusId, int reduction) throws IOException {

        if (reduction <= 0) {
            return 0;
        }

        Customer customer = getCustomerById(cusId);

        if (customer.balance < reduction) {
            return 0;
        }
        customer.balance -= reduction;

        DataManager.getInstance().commit();

        return reduction;
    }

    public static void addVideoHistory(String cusId, String videoId) throws IOException {

        Customer customer = getCustomerById(cusId);

        customer.videosHistory.add(videoId);

        DataManager.getInstance().commit();
    }

    public static void watchVideo(String cusId, String videoId) throws IOException {

        VideoManager.getVideoById(videoId).viewsCount++;
        addVideoHistory(cusId, videoId);
        DataManager.getInstance().commit();
    }
    /**
     *
     * @param cusId
     * @return does operation succeed
     */
    public static boolean extendMembership(String cusId) throws IOException {
        int reduceMount = 100;

        if (decreaseBalance(cusId, reduceMount) != reduceMount) {
            return false;
        }

        Transaction transaction = new Transaction();
        transaction.transactionId = null;
        transaction.cusId = cusId;
        transaction.mount = reduceMount;
        transaction.time = new Date();

        TransactionManager.writeTransaction(transaction);

        return true;
    }

    public static Customer getCustomerById(String cusId) throws IOException {
        ArrayList<Customer> customers = DataManager.getInstance().customers;
        for (Customer customer : customers) {
            if (customer.cusId.equals(cusId)) {
                return customer;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        extendMembership("Cs13");
        watchVideo("Cs13", "asdf");
    }
}
