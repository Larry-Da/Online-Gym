package org.qmbupt.grp105.backend.request;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class utils {
    
    protected static int getAgeByBirthday(String birthdayStr) throws Exception {

        int nowYear = Calendar.getInstance().get(Calendar.YEAR);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date birthdayDate = sdf.parse(birthdayStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthdayDate);
        

        int birthdayYear = cal.get(Calendar.YEAR);

        return nowYear - birthdayYear;
    }

    protected static int getRemainDays(String expireDateStr) throws Exception {
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenSecondsLater = now.plusSeconds(10);
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expireDate = sdf.parse(expireDateStr);
        
        return (int)ChronoUnit.DAYS.between(now, expireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
