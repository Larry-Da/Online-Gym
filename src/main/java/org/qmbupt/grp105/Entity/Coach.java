package org.qmbupt.grp105.Entity;
public class Coach extends Person
{
    private String coachId;
    private int age;
    private char gender;

    public Coach(String coachId, int age, String name, String password, String phoneNo, String email, char gender) {
        super(name, password, phoneNo, email);
        this.coachId = coachId;
        this.age = age;
        this.gender = gender;
    }
    public static Coach getSample()
    {
        return new Coach("01", 29, "John Walker", "abc", "18055615556", "2018213144@bupt.edu.cn", 'M');
    }

    public String getCoachId() {
        return coachId;
    }
    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Coach [coachId=" + coachId + ", age=" + age + ", name=" + name + ", password=" + password + ", phoneNo="
                + phoneNo + ", email=" + email + ", gender=" + gender + "]";
    }
}
