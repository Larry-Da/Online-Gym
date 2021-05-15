package org.qmbupt.grp105.Entity;

import java.util.ArrayList;
import java.util.Date;

public class Customer
{
    private String cusId;
    private int age;
    private char gender;
    private Date dateOfBirth;
    private String membershipLevel;//不确定是String类型的
    private String expiredTime;//会员剩余时间
    private int balance;//余额
    private int points; //积分
    private ArrayList<String> videosHistory;
    private ArrayList<String> favouriteVideos;
    //private String exerciseLevel;
    protected String name;
    protected String password;
    protected String phoneNo;
    protected String email;

    public ArrayList<String> getFavouriteVideos() {
        return favouriteVideos;
    }

    public void setFavouriteVideos(ArrayList<String> favouriteVideos) {
        this.favouriteVideos = favouriteVideos;
    }

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

    public String getExpiredTime() {
        return expiredTime;
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
        this.expiredTime = remainTime;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<String> getVideosHistory() {
        return videosHistory;
    }

    public void setVideosHistory(ArrayList<String> videosHistory) {
        this.videosHistory = videosHistory;
    }
    public static String[] getAllAttibutes() {
        return new String[]{"cusId","age","name","password","phoneNo","email","gender","dateOfBirth","membershipLevel","remainTime","balance","points"};
    }
    public Customer(String cusId, int age, String name, String password, String phoneNo, String email, char gender,
                    Date dateOfBirth, String membershipLevel, String expiredTime, int balance, int points) {
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.cusId = cusId;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.membershipLevel = membershipLevel;
        this.expiredTime = expiredTime;
        this.balance = balance;
        this.points = points;
    }
    private ArrayList<String> bookedSessions;

    public ArrayList<String> getBookedSessions() {
        return bookedSessions;
    }

    public void setBookedSessions(ArrayList<String> bookedSessions) {
        this.bookedSessions = bookedSessions;
    }

    public static Customer getSample()
    {
        return new Customer("1", 21, "Vae", "12345", "18055661111", "2018213144@bupt.edu.cn", 'M', new Date(100000000), "Lv1", "12h", 123, 12);
    }

    public Customer(String cusId, int age, String name, String password, String phoneNo, String email, char gender,
                    Date dateOfBirth, String membershipLevel, String expiredTime, int balance, int points,
                    ArrayList<String> videosHistory, ArrayList<String> bookedSessions, ArrayList<String> favouriteVideos) {

        this.bookedSessions = bookedSessions;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.cusId = cusId;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.membershipLevel = membershipLevel;
        this.expiredTime = expiredTime;
        this.balance = balance;
        this.points = points;
        this.videosHistory = videosHistory;
        this.favouriteVideos = favouriteVideos;
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
                ", expiredTime=" + expiredTime +
                ", balance=" + balance +
                ", points=" + points +
                '}';
    }
    public org.qmbupt.grp105.backend.model.Customer convert()
    {
        org.qmbupt.grp105.backend.model.Customer customer = new org.qmbupt.grp105.backend.model.Customer();
        customer.balance = this.balance;
        customer.cusId = this.cusId;
        customer.expireDate = new Date();
        customer.videosHistory = (ArrayList<String>)this.videosHistory.clone();
        customer.bookedSessions = new ArrayList<>();
        customer.dateOfBirth = this.dateOfBirth;
        customer.email = this.email;
        customer.gender = this.gender + "";
        customer.level = Integer.parseInt(this.membershipLevel);
        customer.name = this.name;
        customer.password = this.password;
        customer.phoneNo = this.phoneNo;
        customer.xp = 0;
        customer.favoriteVideos = this.favouriteVideos;
        return customer;
    }

}
