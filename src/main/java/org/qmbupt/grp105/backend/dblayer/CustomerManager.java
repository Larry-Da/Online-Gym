package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;

import com.alibaba.fastjson.*;

import org.qmbupt.grp105.backend.model.Customer;

public class CustomerManager {

    private static final String JSON_FILE_NAME = "customer.json";

    public static int increaseBalance(String cusId, int addition) throws IOException {

        if (addition <= 0) {
            return 0;
        }

        JSONArray customers = JSON.parseArray(IO.read(JSON_FILE_NAME));

        for (int i = 0; i < customers.size(); i++) {
            if (customers.getJSONObject(i).getString("cusId").equals(cusId)) {
                
                Customer customer = customers.getObject(i, Customer.class);
                customer.balance += addition;
                customers.remove(i);
                customers.add(customer);

                break;
            }
        }

        IO.write(JSON_FILE_NAME, customers.toJSONString());

        return addition;
    }

    public static int decreaseBalance(String cusId, int reduction) throws IOException {
        if (reduction <= 0) {
            return 0;
        }

        JSONArray customers = JSON.parseArray(IO.read(JSON_FILE_NAME));

        for (int i = 0; i < customers.size(); i++) {
            if (customers.getJSONObject(i).getString("cusId").equals(cusId)) {
                
                Customer customer = customers.getObject(i, Customer.class);
                if (customer.balance < reduction) {
                    return 0;
                }
                customer.balance -= reduction;
                customers.remove(i);
                customers.add(customer);

                break;
            }
        }

        IO.write(JSON_FILE_NAME, customers.toJSONString());

        return reduction;
    }

    public static void extendMembership(String cusId) {
        
    }

    public static void writeCustomerInfo(Customer customer) throws IOException {

        JSONArray customers = JSON.parseArray(IO.read(JSON_FILE_NAME));

        for (int i = 0; i < customers.size(); i++) {
            if (customer.cusId.equals(customers.getJSONObject(i).getString("cusId"))) {
                customers.remove(i);
                break;
            }
        }
        
        customers.add(customer);
        
        IO.write(JSON_FILE_NAME, customers.toJSONString());
    }

    public static void main(String[] args) throws Exception {
        JSONArray customers = JSON.parseArray(IO.read(JSON_FILE_NAME));
        Customer customer0 = customers.getObject(1, Customer.class);

        System.out.println(customer0.videosHistory.get(0));

        increaseBalance("Cs13", 12345);
    }
}
