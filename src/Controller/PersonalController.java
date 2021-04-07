package Controller;

import Entity.*;
import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PersonalController extends Request {

    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;
    public PersonalController() {};

    /**
     * <p>
     *     This function gets all live sessions which are booked by a given customer
     * </p>
     * @param cusId  customer ID
     * @return  a list of live sessions which are booked by a specific customer
     */
    public ArrayList<LiveSession> getSession(String cusId) {
        ArrayList<LiveSession> sessions = new ArrayList<>();
        param.put("cusId", "C123456");
        request = new Request("getSessionIdsByCusId", param);
//        response = new Response(backend.getSessionIdsByCusId);
        response = new Response("{\"status\":\"success\",\"payload\":{\"sessionIds\":[\"s001\",\"s002\"]}}");
        String status = response.getStatus();
        param.clear();

        if(status.equalsIgnoreCase("success")) {
            JsonArray jsonArray =  response.getPayload().getAsJsonArray("sessionIds");
            List<String> sessionIds = gson.fromJson(jsonArray,List.class);
            for(String sessionId : sessionIds) {
                param.put("sessionId",sessionId);
                request = new Request("getLiveSessionInfo", param);
//                response = new Response(backend.getLiveSessionInfo(request));
                response = new Response( "{\"status\":\"success\",\"payload\":{\"liveSessionId\":\"L001\",\"url\":\"usr/local/bin\",\"rating\":\"7.8\",\"category\":\"Yoga\",\"startTime\":\"2020-05-01\",\"endTime\":\"2020-07-02\",\"likes\":\"100\",\"viewCounts\":\"3000\",\"Customer_cusId\":\"C001\",\"Coach_coachId\":\"Ch001\"}}");
                if(response.getStatus().equalsIgnoreCase("success")) {
                    LiveSession liveSession = gson.fromJson(response.getPayload(), LiveSession.class);
                    sessions.add(liveSession);
                }
                param.clear();
            }
            return sessions;
        }
        return null;
    }

    /**
     * <p>
     *     This function gets all information of all customers
     * </p>
     * @return a list of all customers
     */
    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();
//        response = new Response(backend.getAllCustomers());
        response = new Response("{\"status\":\"success\",\"payload\":{\"cusIds\":[\"C001\",\"C002\"]}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("cusIds");
            List<String> customerIds = gson.fromJson(jsonArray, List.class);
            for(String customerId : customerIds) {
                Customer customer = getCusInfoByCusId(customerId);
                customers.add(customer);
            }
            return customers;
        }
        return null;
    }

    /**
     * <p>
     *     This function gets all information of a given customer
     * </p>
     * @param cusId customer ID
     * @return  an customer entity
     */
    public Customer getCusInfoByCusId(String cusId) {
        param.put("cusId",cusId);
        request = new Request("getCutomerById", param);
//        response = new Response(backend.getCustomerById(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"cusId\":\"C001\",\"age\":\"45\",\"name\":\"goteng\",\"password\":\"1234566\",\"phoneNo\":\"18235226823\",\"email\":\"1770927746@qq.com\",\"gender\":\"M\",\"dateOfBirth\":\"2000-04-17\",\"membershipLevel\":\"L1\",\"remainTime\":\"345\",\"balance\":\"12345\",\"points\":\"300\"}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            Customer customer = gson.fromJson(response.getPayload(), Customer.class);
            param.clear();
            return customer;
        }
        return null;
    }

    public Customer getCusInfoById(String cusId) {
        return getCusInfoByCusId(cusId);
    }

    /**
     * <p>
     *     This function gets the customer by his name
     * </p>
     * @param name
     * @return a specific customer entity
     */
    public Customer getCusInfoByName(String name) {
        param.put("cusName",name);
        request = new Request("getCutomerByName", param);
//        response = new Response(backend.getCustomerByName(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"cusId\":\"C001\",\"age\":\"45\",\"name\":\"goteng\",\"password\":\"1234566\",\"phoneNo\":\"18235226823\",\"email\":\"1770927746@qq.com\",\"gender\":\"M\",\"dateOfBirth\":\"2000-04-17\",\"membershipLevel\":\"L1\",\"remainTime\":\"345\",\"balance\":\"12345\",\"points\":\"300\"}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            Customer customer = gson.fromJson(response.getPayload(), Customer.class);
            param.clear();
            return customer;
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
    public ArrayList<Customer> getCusByGender(String gender) {
        ArrayList<Customer> customers = new ArrayList<>();
        param.put("gender",gender);
        request = new Request("getCusInfoByGender",param);
//        response = new Response(backend.getCusInfoByGender(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"cusIds\":[\"C001\",\"C002\"]}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("cusIds");
            List<String> cusIds = gson.fromJson(jsonArray, List.class);
            for(String cusId : cusIds) {
                Customer customer = getCusInfoByCusId(cusId);
                customers.add(customer);
            }
            param.clear();
            return customers;
        }
        return null;
    }

    /**
     * <p>
     *     This function gets the number of customer for a given membership level
     * </p>
     * @param level a specific level, such as L1
     * @return the number of customer
     */
    public int getCusNumByLevel(String level) {
        param.put("membershipLevel", level);
        request = new Request("getCusNumByLevel", param);
//        response = new Response(backend.getCusNumByLevel(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"num\":\"100\"}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            JsonObject jsonObject = response.getPayload();
            Integer num = Integer.parseInt(jsonObject.get("num").getAsString());
            param.clear();
            return num;
        }
        return -1;
    }

    /**
     * <p>
     *     This function gets income of a given month
     * </p>
     * @param month a specific month
     * @return the amount of money earned by the gym during a given month
     */
    public int getMonthlyIncome(String month) {
        param.put("month", month);
        request = new Request("getMonthlyIncome", param);
//        response = new Response(backend.getMonthlyIncome(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"income\":\"100\"}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            JsonObject jsonObject = response.getPayload();
            Integer num = Integer.parseInt(jsonObject.get("income").getAsString());
            param.clear();
            return num;
        }
        return -1;
    }

    /**
     * <p>
     *     This function returns the expire time of a given customer' membership
     * </p>
     * @param cusId customer ID
     * @return  expire time of a given customer's membership
     */
    public Date getExpireTimeByCusId(String cusId) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        param.put("cusId",cusId);
        request = new Request("getExpireTimeByCusId", param);
//        response = new Response(back.getExpireTimeByCusId(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"Expiretime\":\"2020-05-09\"}}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            try {
                Date date = sf.parse(response.getPayload().get("Expiretime").getAsString());
                param.clear();
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *<p>
     *     This function gets all videos which a given customer has watched before
     *</p>
     * @param cusId customer ID
     * @return a list of videos
     */
    public ArrayList<Video> getVideosByCusId(String cusId) {
        ArrayList<Video> videos = new ArrayList<>();
        param.put("cusId",cusId);
        request = new Request("getVideoIdsByCusId",param);
//        response = new Response(backend.getVideosByCusId(request));
        response = new Response("{\"status\":\"success\",\"payload\":{\"videoIds\":[\"V001\",\"V002\"]}}");
        String status = response.getStatus();
        param.clear();
        if(status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("videoIds");
            List<String> videoIds = gson.fromJson(jsonArray, List.class);
            for(String videoId : videoIds) {
                param.put("videoId", videoId);
                request = new Request("getVideoInfo",param);
//                response = new Response(backend.getVideoInfo(request));
                response = new Response("{\"status\":\"success\",\"payload\":{\"videoId\":\"V001\",\"url\":\"usr/local/bin\",\"name\":\"strength\",\"rating\":\"7.8\",\"category\":\"Yoga\",\"likes\":\"100\",\"viewCounts\":\"3000\",\"level\":\"easy\"}}");
                if(response.getStatus().equalsIgnoreCase("success")) {
                    Video video = gson.fromJson(response.getPayload(),Video.class);
                    videos.add(video);
                }
                param.clear();
            }
        }
        return videos;
    }

    /**
     * <p>
     *     This function adds a new video
     * </p>
     * @param video a video entity
     * @return return true if add successfully, otherwise return false
     */
    public boolean AddVideo(Video video) {
        String json = gson.toJson(video);
        param = gson.fromJson(json, Map.class);
        request = new Request("addVideo",param);
//        response = new Response(backend.addVideo(request));
        response = new Response("{\"status\":\"success\"}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     *     This function modifies a given video
     * </p>
     * @param video a modified video entity
     * @return  return true if modification works, otherwise return false
     */
    public boolean modifyVideo(Video video) {
        String json = gson.toJson(video);
        param = gson.fromJson(json, Map.class);
        request = new Request("modifyVideo",param);
//        response = new Response(backend.modifyVideo(request));
        response = new Response("{\"status\":\"success\"}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        try {
            JsonParser parser=new JsonParser();
            JsonObject object=(JsonObject) parser.parse(new FileReader("src/user.json"));
            System.out.println(object.toString());

        }
        catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
/*
{
  "status": "success",
  "payload": {
    "liveSessionId": "L001",
    "url": "usr/local/bin",
    "rating": "7.8",
    "category": "Yoga",
    "startTime": "2020-05-01",
    "endTime": "2020-07-02",
    "likes": "100",
    "viewCounts": "3000",
    "Customer_cusId": "C001",
    "Coach_coachId": "Ch001"
  }
}
{
  "status": "success",
  "payload": {
    "cusId": "C001",
    "age": "45",
    "name": "goteng",
    "password": "1234566",
    "phoneNo": "18235226823",
    "email": "1770927746@qq.com",
    "gender": "M",
    "dateOfBirth": "2000-04-17",
    "membershipLevel": "L1",
    "remainTime": "345",
    "balance": "12345",
    "points": "300"
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
    "rating": "7.8",
    "category": "Yoga",
    "likes": "100",
    "viewCounts": "3000",
    "level": "easy"
  }
}
{
  "status": "success",
  "payload": {
    "num": "100"
  }
}
 */