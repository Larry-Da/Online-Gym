package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.*;
import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.qmbupt.grp105.backend.BackendServer;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
/**
 * @version 1.2
 * @author Wenrui Zhao
 */
public class PersonalController {

    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;
    private static BackendServer backendServer;
    private static PersonalController personalController = new PersonalController();
    private PersonalController() {};
    public  static PersonalController getController()
    {
        return personalController;
    }



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
        try {
            return CustomerManager.getCustomerById(cusId).converter();
        }

        catch (Exception e){

        }
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
    public String getIdByEmail(String email)
    {
        ArrayList<Customer> customers = getAllCustomer();
        for(Customer c: customers) {
            if(c.getEmail().equals(email)) {
                return c.getCusId();
            }
        }
        return null;
    }
    public void updateCustomer(Customer customer) {
        try {
            CustomerManager.writeCustomerInfo(customer.convert());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void increaseBalance(String cusId, int num) {
        try {
            CustomerManager.increaseBalance(cusId, num);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void decreaseBalance(String cusId, int num) {
        try {
            CustomerManager.decreaseBalance(cusId, num);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void bookLiveSession(String cusId, String sessionId) {
        try {
            CustomerManager.bookSession(cusId, sessionId);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void watchVideo(String cusId, String videoId) {
        try {
            CustomerManager.watchVideo(cusId, videoId);
        } catch(IOException e) {
            e.printStackTrace();
        }
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
        return customer.getExpiredTime();
    }

    public void extendMembership(String cusId) {
        try {
            CustomerManager.extendMembership(cusId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
//    public static void main(String[] args) {
//        try {
//            JsonParser parser=new JsonParser();
//            JsonObject object=(JsonObject) parser.parse(new FileReader("src/main/resources/test.json"));
//            System.out.println(object.toString());
//            //Video video = new Video("V008","usr/local/bin","sldfjslfjskfjslkdfjsl",8.7,"Yoga",100,1000);
//
//        }
//        catch (JsonIOException e) {
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            e.printStackTrace();
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//
//    }
/*
{
  "status": "success",
  "payload": {
    "liveSessionId": "L001",
    "url": "usr/local/bin",
    "rating": 7.8,
    "category": "Yoga",
    "startTime": "2020-05-01",
    "endTime": "2020-07-02",
    "likes": 100,
    "viewCounts": 3000,
    "Customer_cusId": "C001",
    "Coach_coachId": "Ch001"
  }
}
{
  "status": "success",
  "payload": {
    "cusId": "C001",
    "age": 45,
    "name": "goteng",
    "password": "1234566",
    "phoneNo": "18235226823",
    "email": "1770927746@qq.com",
    "gender": "M",
    "dateOfBirth": "2000-04-17",
    "membershipLevel": "L1",
    "remainTime": 345,
    "balance": 12345,
    "points": 300
  }
}
{
  "status": "success",
  "payload": {
    "Expiretime": "2020-05-09"
  }
}
{
  "status": "success",
  "payload": {
    "videoIds": ["V001","V002"]
  }
}
{
  "status": "success",
  "payload": {
    "videoId": "V001",
    "url": "usr/local/bin",
    "name": "strength",
    "rating": 7.8,
    "category": "Yoga",
    "likes": 100,
    "viewCounts": 3000,
    "level": "easy"
  }
}
{
  "status": "success",
  "payload": {
    "num": 100
  }
}
 */