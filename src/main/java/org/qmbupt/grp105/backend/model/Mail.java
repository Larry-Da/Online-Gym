package org.qmbupt.grp105.backend.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Mail {
    @JSONField(name = "from")
    public String from;

    @JSONField(name = "to")
    public String to;

    @JSONField(name = "time", format = "yyyy-MM-dd hh:mm:ss")
    public Date time;

    @JSONField(name = "content")
    public String content;


}
