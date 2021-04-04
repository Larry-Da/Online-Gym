package request;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


import com.alibaba.fastjson.*;


public class RequestGetCustomerByIdHandler implements RequestHandler {

    
    @Override
    public String execute(String payload) {
        JSONObject retJson = new JSONObject();
        
        try {
            String cusId = JSON.parseObject(payload).getString("cusId");
            JSONArray customers = JSON.parseArray(IO.read("customer.json"));
            
            for (int i = 0; i < customers.size(); i++) {
                JSONObject customer = customers.getJSONObject(i);
                if (customer.getString("cusId").equals(cusId)) {

                    JSONObject responsePayload = new JSONObject();

                    responsePayload.put("cusId", customer.getString("cusId"));
                    responsePayload.put("age", "" + getAgeByBirthday(customer.getString("dateOfBirth")));
                    responsePayload.put("name", customer.getString("name"));
                    responsePayload.put("password", customer.getString("password"));
                    responsePayload.put("phoneNo", customer.getString("phoneNo"));
                    responsePayload.put("email", customer.getString("email"));
                    responsePayload.put("gender", customer.getString("gender"));
                    responsePayload.put("dateOfBirth", customer.getString("dateOfBirth"));
                    responsePayload.put("membershipLevel", "L" + customer.getString("membershipLevel"));
                    if (customer.getString("expireDate") != null)
                        responsePayload.put("remainTime", "" + getRemainDays(customer.getString("expireDate")));
                    else
                        responsePayload.put("remainTime", "" + 0);
                    responsePayload.put("balance", "" + customer.getIntValue("balance"));
                    responsePayload.put("points", "" + customer.getIntValue("xp"));
                    
                    
                    retJson.put("payload", responsePayload);

                    retJson.put("status", "success");
                }
            }

        } catch (Exception ex) {
            retJson.put("status", "failed");
            ex.printStackTrace();
        }

        return retJson.toJSONString();
    }

    private static int getAgeByBirthday(String birthdayStr) throws Exception {
    
        int nowYear = Calendar.getInstance().get(Calendar.YEAR);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date birthdayDate = sdf.parse(birthdayStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthdayDate);
        

        int birthdayYear = cal.get(Calendar.YEAR);

        return nowYear - birthdayYear;
    }

    private static int getRemainDays(String expireDateStr) throws Exception {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenSecondsLater = now.plusSeconds(10);
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expireDate = sdf.parse(expireDateStr);

        return (int)ChronoUnit.DAYS.between(now, expireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
