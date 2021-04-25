package org.qmbupt.grp105.Entity;
import javax.websocket.Session;
import java.util.Date;
public class LiveSession {
    private String liveSessionId;
    private String url;
    private Double rating; // 评分 int score
    private String category;
    private Date startTime; // 开始结束
    private Date endTime;
    private int likes;      // 点赞数
    private int viewCounts; // 访问量 -> hottest
    private String Customer_cusId;
    private String Coach_coachId;
    private int availableNum;
    public static String[] getAllAttributes() {
        return new String[]{"liveSessionId","url","rating","category","startTime","endTime","likes","viewCounts","Customer_cusId","Coach_coachId","availableNum"};
    }
    public LiveSession(String liveSessionId, String url, Double rating, String category, Date startTime, Date endTime,
                       int likes, int viewCounts, String customer_cusId, String coach_coachId, int availableNum) {
        this.liveSessionId = liveSessionId;
        this.url = url;
        this.rating = rating;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.likes = likes;
        this.viewCounts = viewCounts;
        Customer_cusId = customer_cusId;
        Coach_coachId = coach_coachId;
        this.availableNum = availableNum;
    }
    public static LiveSession getSample()
    {
        return new LiveSession("1", "PersonalIcon.jpg", 8.1, "Yoga", new Date(100000000), new Date(1000000000), 20, 100, "", "2", 10);
    }



    public String getLiveSessionId() {
        return liveSessionId;
    }

    public String getUrl() {
        return url;
    }

    public Double getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getLikes() {
        return likes;
    }

    public int getViewCounts() {
        return viewCounts;
    }

    public String getCustomer_cusId() {
        return Customer_cusId;
    }

    public String getCoach_coachId() {
        return Coach_coachId;
    }

    public void setLiveSessionId(String liveSessionId) {
        this.liveSessionId = liveSessionId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setViewCounts(int viewCounts) {
        this.viewCounts = viewCounts;
    }

    public void setCustomer_cusId(String customer_cusId) {
        Customer_cusId = customer_cusId;
    }

    public void setCoach_coachId(String coach_coachId) {
        Coach_coachId = coach_coachId;
    }

    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }

    @Override
    public String toString() {
        return "LiveSession{" +
                "liveSessionId='" + liveSessionId + '\'' +
                ", url='" + url + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", likes=" + likes +
                ", viewCounts=" + viewCounts +
                ", Customer_cusId='" + Customer_cusId + '\'' +
                ", Coach_coachId='" + Coach_coachId + '\'' +
                ", availableNum=" + availableNum +
                '}';
    }
}
