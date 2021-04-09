package org.qmbupt.grp105.backend.model;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

public class Customer {

    @JSONField(name = "cusId")
    public String cusId;

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "password")
    public String password;

    @JSONField(name = "phoneNo")
    public String phoneNo;

    @JSONField(name = "email")
    public String email;

    @JSONField(name = "gender")
    public String gender;

    @JSONField(name = "dateOfBirth", format = "yyyy-MM-dd", ordinal = 3)
    public String dateOfBirth;

    @JSONField(name = "level")
    public int level;

    @JSONField(name = "expireDate")
    public String expireDate;

    @JSONField(name = "balance")
    public int balance;

    @JSONField(name = "xp")
    public int xp;
    
    @JSONField(name = "videosHistory")
    public ArrayList<String> videosHistory;
}
