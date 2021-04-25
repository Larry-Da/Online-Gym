package org.qmbupt.grp105.backend.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Coach {
    @JSONField(name = "coachId")
    public String coachId;

    @JSONField(name = "age")
    public int age;

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "password")
    public String password;

    @JSONField(name = "phoneNo")
    public String phoneNo;

    @JSONField(name = "email")
    public String email;

    @JSONField(name = "gender")
    public char gender;
}
