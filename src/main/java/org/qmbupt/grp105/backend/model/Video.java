package org.qmbupt.grp105.backend.model;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

public class Video {
    
    @JSONField(name = "videoId")
    public String videoId;

    @JSONField(name = "url")
    public String url;

    @JSONField(name = "name")
    public String name;

//  public String videoDescription;
    
    @JSONField(name = "rating")
    public Double rating;

    @JSONField(name = "category")
    public String category;

    @JSONField(name = "likes")
    public int likes;

    @JSONField(name = "viewsCount")
    public int viewsCount;

    @JSONField(name = "level")
    public String level;

    @JSONField(name = "comments")
    public ArrayList<String> comments;

    public org.qmbupt.grp105.Entity.Video converter() {
        return new org.qmbupt.grp105.Entity.Video(this.videoId, this.url, this.name, this.rating, this.category,
                this.likes, this.viewsCount, this.level);
    }


}
