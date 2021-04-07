package Entity;

public class Coach {
    private String coachId;
    private int age;
    private String name;
    private String password;
    private String phoneNo;
    private String email;
    private char gender;

    public Coach(String coachId, int age, String name, String password, String phoneNo, String email, char gender) {
        super();
        this.coachId = coachId;
        this.age = age;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.gender = gender;
    }
    public Coach() {
        super();
        // TODO Auto-generated constructor stub
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
