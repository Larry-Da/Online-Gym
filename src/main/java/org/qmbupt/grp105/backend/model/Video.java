package org.qmbupt.grp105.backend.model;

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

}
