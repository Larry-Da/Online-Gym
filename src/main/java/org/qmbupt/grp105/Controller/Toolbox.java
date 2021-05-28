package org.qmbupt.grp105.Controller;
import org.qmbupt.grp105.Entity.Coach;
import org.qmbupt.grp105.UI.UIStyle;

import java.util.ArrayList;
import java.util.regex.*;

import static org.qmbupt.grp105.Controller.PersonalController.getController;

public class Toolbox
{
    public static final String dateForm1Format = "yyyy-mm-dd";
    public static final String dateForm2Format = "yyyy-mm-dd hh:mm:ss";
    public static final String emailFormat = "xxx@yy.zz.ww";
    public static final String passwordFormat = "more than 6 digits and contains at least one number and one letter";
    public static final String genderFormat = "M or F";

    public static boolean isDateForm1(String date)
    {
        return Pattern.matches("^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$", date);
    }
    public static boolean isDateForm2(String date)
    {
        String yyyymmdd = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))";
        String hhmmss = "[ ]([01][1-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        return Pattern.matches(yyyymmdd + hhmmss, date);
    }
    public static boolean isEmail(String email)
    {
        String emailPattern = "^[\\w]+@([\\w]+.)+[\\w]+$";
        return Pattern.matches(emailPattern, email);
    }
    public static boolean isPassword(String password)
    {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
        return Pattern.matches(passwordPattern, password);
    }
    public static boolean isGender(String gender)
    {
        return Pattern.matches("^[MF]$", gender);
    }
    public static boolean isPicture(String picture)
    {
        String picPattern = "^.*(.png|.jpeg|.jpg|.bmp)$";
        return Pattern.matches(picPattern, picture);
    }
    public static boolean isCategory(String category)
    {
        String[] a = UIStyle.categories;
        for(String i: a)
        {
            if(i.equals(category))
                return true;
        }
        return false;
    }
    public static boolean isMembership(String membership)
    {
        String[] a = UIStyle.memberships;
        for(String i : a)
        {
            if(i.equals(membership))
                return true;
        }
        return false;
    }


    public static boolean isCoachID(String coachID)
    {
        ArrayList<Coach> coaches = PersonalController.getController().getAllCoaches();
        for(Coach c: coaches)
        {
            if(c.getCoachId().equals(coachID))
            {
                return true;
            }
        }
        return false;
    }



}
