package Entity;

import java.util.Date;

public class Customer {
    private String cusId;
    private int age;
    private String name;
    private String password;
    private String phoneNo;
    private String email;
    private char gender;
    private Date dateOfBirth;
    private String membershipLevel;//不确定是String类型的
    private String remainTime;//会员剩余时间
    private int balance;//余额
    private int points; //积分
    //private String exerciseLevel;

    public String getCusId() {
        return cusId;
    }

    public int getAge() {
        return age;
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

    public char getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public int getBalance() {
        return balance;
    }

    public int getPoints() {
        return points;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public void setAge(int age) {
        this.age = age;
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

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Customer(String cusId, int age, String name, String password, String phoneNo, String email, char gender,
                    Date dateOfBirth, String membershipLevel, String remainTime, int balance, int points) {
        this.cusId = cusId;
        this.age = age;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.membershipLevel = membershipLevel;
        this.remainTime = remainTime;
        this.balance = balance;
        this.points = points;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "cusId='" + cusId + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", remainTime='" + remainTime + '\'' +
                ", balance=" + balance +
                ", points=" + points +
                '}';
    }
}
