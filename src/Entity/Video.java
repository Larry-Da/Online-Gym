package Entity;

public class Video {
    private String videoId;
    private String url;
    private String name; // for keyword search
    //  private String videoDescription;
    private Double rating; // 评分 int score
    private String category;
    private int likes;      // 点赞数
    private int viewCounts; // 访问量 -> hottest
    private String level;

//    public ArrayList<LiveSession> getLiveSessions() {
//        backend.getLiveSession(cusId);
//        backend.getLiveSessionInfo()
//    }
//    public ArrayList<Videos> getVideoHistory();

    public Video(String videoId, String url, String name, Double rating, String category, int likes, int viewCounts) {
        this.videoId = videoId;
        this.url = url;
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.likes = likes;
        this.viewCounts = viewCounts;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViewCounts() {
        return viewCounts;
    }

    public void setViewCounts(int viewCounts) {
        this.viewCounts = viewCounts;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", likes=" + likes +
                ", viewCounts=" + viewCounts +
                '}';
    }
}
