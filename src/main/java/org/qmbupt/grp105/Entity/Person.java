package org.qmbupt.grp105.Entity;

public class Person
{
    protected String name;
    protected String password;
    protected String phoneNo;
    protected String email;

    public Person(String name, String password, String phoneNo, String email) {
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
