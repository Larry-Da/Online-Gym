package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.*;
import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.qmbupt.grp105.backend.BackendServer;
public class PersonalController {

    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;
    private static BackendServer backendServer;
    public PersonalController() {};


    /**
     * <p>
     *     This function gets all information of all customers
     * </p>
     * @return a list of all customers
     */
    public ArrayList<Customer> getAllCustomer() {
//        param.put("type","Customer");
//        param.put("fields",new String[]{"cusId"});
        ArrayList<Customer> customers = new ArrayList<>();
        request = new Request("getCustomerIds",param);
        response = new Response(backendServer.execute(request.toJsonString()));
//        response = new Response("{\"status\":\"success\",\"payload\":{\"cusIds\":[\"C001\",\"C002\"]}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("cusIds");
            List<String> customerIds = gson.fromJson(jsonArray, List.class);
            for(String customerId : customerIds) {
                Customer customer = getCusInfoByCusId(customerId);
                customers.add(customer);
            }
            param.clear();
            return customers;
        }
        param.clear();
        return null;
    }

    public int getNumOfAllCustomers() {
        ArrayList<Customer> customers = getAllCustomer();
        return customers.size();
    }
    public List<Customer> getCustomerByPage(int start, int end) {
        ArrayList<Customer> customers = getAllCustomer();
        if(start >= customers.size() || end > customers.size()) {
            return null;
        }
        return customers.subList(start, end);
    }
    /**
     * <p>
     *     This function gets all information of a given customer
     * </p>
     * @param cusId customer ID
     * @return  an customer entity
     */
    public Customer getCusInfoByCusId(String cusId) {
//        param.put("type","Customer");
//        param.put("id",cusId);
//        param.put("fields",Customer.getAllAttibutes());
        param.put("cusId",cusId);
        request = new Request("getCustomerById", param);
        response = new Response(backendServer.execute(request.toJsonString()));
//        response = new Response("{\"status\":\"success\",\"payload\":{\"cusId\":\"C001\",\"age\":45,\"name\":\"goteng\",\"password\":\"1234566\",\"phoneNo\":\"18235226823\",\"email\":\"1770927746@qq.com\",\"gender\":\"M\",\"dateOfBirth\":\"2000-04-17\",\"membershipLevel\":\"L1\",\"remainTime\":345,\"balance\":12345,\"points\":300}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            Customer customer = gson.fromJson(response.getPayload(), Customer.class);
            param.clear();
            return customer;
        }
        param.clear();
        return null;
    }



    /**
     * <p>
     *     This function gets the customer by his name
     * </p>
     * @param name
     * @return a specific customer entity
     */
    public Customer getCusInfoByName(String name) {
        ArrayList<Customer> customers = getAllCustomer();
        for(Customer customer : customers) {
            if(customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * <p>
     *     This function gets a list of customers of the same gender
     * </p>
     * @param gender male or female
     * @return a list of customers
     */
    public ArrayList<Customer> getCusByGender(char gender) {
        ArrayList<Customer> customers = getAllCustomer();
        ArrayList<Customer> cuslist = new ArrayList<>();
        for(Customer customer : customers) {
            if(customer.getGender() == gender) {
                cuslist.add(customer);
            }
        }
        return cuslist;
    }

    /**
     * <p>
     *     This function gets the number of customer for a given membership level
     * </p>
     * @param level a specific level, such as L1
     * @return the number of customer
     */
    public int getCusNumByLevel(String level) {
        ArrayList<Customer> customers = getAllCustomer();
        int num = 0;
        for(Customer customer : customers) {
            if(customer.getMembershipLevel().equalsIgnoreCase(level)) {
                num++;
            }
        }
        return num;
    }
    public boolean updateCustomer(Customer customer) {
        String json = gson.toJson(customer);
        param = gson.fromJson(json,Map.class);
        request = new Request("updateCustomer",param);
//        response = new Response(backend.updateCustomer(request));
        response = new Response("{\"status\":\"success\"}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            param.clear();
            return true;
        }
        param.clear();
        return false;
    }


    /**
     * <p>
     *     This function gets income of a given month
     * </p>
     * @param month a specific month
     * @return the amount of money earned by the gym during a given month
     */
    public int getMonthlyIncome(int month) {
        param.put("month", month);
        request = new Request("getMonthlyIncome", param);
        response = new Response(backendServer.execute(request.toJsonString()));
//        response = new Response("{\"status\":\"success\",\"payload\":{\"income\":\"100\"}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            JsonObject jsonObject = response.getPayload();
            Integer num = Integer.parseInt(jsonObject.get("income").getAsString());
            param.clear();
            return num;
        }
        param.clear();
        return -1;
    }
    /**
     * <p>
     *     This function returns the expire time of a given customer' membership
     * </p>
     * @param cusId customer ID
     * @return  expire time of a given customer's membership
     */
    public String getExpireTimeByCusId(String cusId) {
        Customer customer = getCusInfoByCusId(cusId);
        return customer.getRemainTime();
    }
    
}