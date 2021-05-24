package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.*;
import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.qmbupt.grp105.backend.BackendServer;
import org.qmbupt.grp105.backend.dblayer.CoachManager;
import org.qmbupt.grp105.backend.dblayer.CustomerManager;
import org.qmbupt.grp105.backend.dblayer.DataManager;
import org.qmbupt.grp105.backend.dblayer.VideoManager;

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
    public ArrayList<Customer> filterByKeyword(ArrayList<Customer> customers, String key)
    {
        ArrayList<Customer> res = new ArrayList<>();
        if(key == null)
            return customers;
        for(Customer i : customers)
        {
            if(i.getName().contains(key) || i.getCusId().contains(key))
            {
                res.add(i);
            }
        }
        return res;
    }
    public ArrayList<Customer> filterByGender(ArrayList<Customer> customers, char gender)
    {
        ArrayList<Customer> res = new ArrayList<>();
        if(gender == 'n')
            return customers;
        for(Customer i: customers)
        {
            if(gender == i.getGender())
                res.add(i);
        }
        return res;
    }
    public ArrayList<Customer> filterByCusLevel(ArrayList<Customer> customers,ArrayList<String> levelKeys)
    {
        ArrayList<Customer> res = new ArrayList<>();
        if(levelKeys.size() == 0)
            return customers;
        if(levelKeys.get(0).equals("l"))
            return customers;
        for(Customer i: customers)
        {
            if(levelKeys.contains(i.getMembershipLevel()))
                res.add(i);
        }
        return res;
    }


    /**
     * <p>
     *     This function gets all information of all customers
     * </p>
     * @return a list of all customers
     */
    public ArrayList<Customer> getAllCustomer() {
        ArrayList<org.qmbupt.grp105.backend.model.Customer> customers = null;
        try {
            customers = DataManager.getInstance().customers;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<Customer> res = new ArrayList<>();
        for(int i = 0; i < customers.size(); i++)
        {
            res.add(customers.get(i).converter());
        }
        return res;
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
    public Coach getCoachInfoById(String coId)
    {
        ArrayList<Coach> coaches = this.getAllCoaches();
        for(Coach i : coaches)
        {
            if(i.getCoachId().equals(coId))
                return i;
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

    /**
     * check the username and the password
     * @param username
     * @param pass
     * @return
     */
    public int check(String username, String pass) {
        try {
            if(CustomerManager.getCustomerById(getIdByEmail(username)) == null) {
                if (CoachManager.getCoachById(getIdByEmail(username)) == null) {
                    return 3;
                }
                else {
                    Coach coach = CoachManager.getCoachById(getIdByEmail(username)).converter();
                    if(!coach.getPassword().equals(pass)) {
                        return 4;
                    }
                    else {
                        return 2;
                    }
                }
            }
            else {
                Customer customer = CustomerManager.getCustomerById(getIdByEmail(username)).converter();
                if(!customer.getPassword().equals(pass)) {
                    return 4;
                }
                else {
                    return 1;
                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    public void updateCustomer(Customer customer) {
        try {
            if(customer.getCusId().equals(""))
            {
                int num = getAllCustomer().size();
                String id = "cs" + (num + 1);
                customer.setCusId(id);
            }
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
            Customer customer = CustomerManager.getCustomerById(cusId).converter();
            if(!customer.getVideosHistory().contains(videoId)) {
                CustomerManager.watchVideo(cusId,videoId);
            }
            else {
                int i = 0;
                int index = customer.getVideosHistory().indexOf(videoId);
                for(i = index; i < customer.getVideosHistory().size() - 1; i++) {
                    customer.getVideosHistory().set(i, customer.getVideosHistory().get(i + 1));
                }
                customer.getVideosHistory().set(i, videoId);
                updateCustomer(customer);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * add the video to personal favourite list
     * @param cusId
     * @param videoId
     */
    public void addToFavourite(String cusId, String videoId) {
        try {
            CustomerManager.addFavoriteVideo(cusId, videoId);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * remove the video from personal favourite list
     * @param cusId
     * @param videoId
     */
    public void removeFromFavourite(String cusId, String videoId) {
        try {
            CustomerManager.removeFavoriteVideo(cusId, videoId);
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
    public ArrayList<Video> getFavouriteVideoByCusId(String cusId) {
        Customer customer = getCusInfoByCusId(cusId);
        ArrayList<Video> res = new ArrayList<>();
        try {
            for(String s: customer.getFavouriteVideos()) {
                res.add(VideoManager.getVideoById(s).converter());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return res;

    }
    public ArrayList<Coach> getAllCoaches() {
        ArrayList<org.qmbupt.grp105.backend.model.Coach> coaches = new ArrayList<>();
        ArrayList<Coach> res = new ArrayList<>();
        try {
            coaches = CoachManager.getAllCoaches();
            for(org.qmbupt.grp105.backend.model.Coach c: coaches) {
                res.add(c.converter());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * get customer's id by his email
     * @param email
     * @return
     */
    public String getIdByEmail(String email)
    {
        ArrayList<Customer> customers = getAllCustomer();
        for(Customer c: customers) {
            if(c.getEmail().equals(email)) {
                return c.getCusId();
            }
        }
        ArrayList<Coach> coaches = getAllCoaches();
        for(Coach c: coaches) {
            if(c.getEmail().equals(email)) {
                return c.getCoachId();
            }
        }
        return null;
    }

    /**
     * extend the membership
     * @param cusId
     */
    public void extendMembership(String cusId) {
        try {
            CustomerManager.extendMembership(cusId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        PersonalController personalController = PersonalController.getController();
        personalController.watchVideo("cs5","v001");
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