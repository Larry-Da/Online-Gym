package org.qmbupt.grp105.backend.request;

import com.alibaba.fastjson.*;

import org.qmbupt.grp105.backend.dblayer.VideoManager;
import org.qmbupt.grp105.backend.model.Video;

public class RequestAddVideoHandler implements RequestHandler {

    @Override
    public String execute(String payload) {
        
        JSONObject retJson = new JSONObject();

        try {

            JSONObject newVideoFromPayload = JSON.parseObject(payload);

            Video video = new Video();
            
            video.videoId    = newVideoFromPayload.getString("videoId");
            video.url        = newVideoFromPayload.getString("url");
            video.name       = newVideoFromPayload.getString("name");
            video.rating     = Double.parseDouble(newVideoFromPayload.getString("rating"));
            video.category   = newVideoFromPayload.getString("category");
            video.likes      = Integer.parseInt(newVideoFromPayload.getString("likes"));
            video.viewsCount = Integer.parseInt(newVideoFromPayload.getString("viewCounts"));
            video.level      = newVideoFromPayload.getString("level");

            VideoManager.writeVideoInfo(video);

            retJson.put("status", "success");

        } catch (Exception ex) {
            ex.printStackTrace();
            retJson.put("status", "failed");
        }

        return retJson.toJSONString();
    }
    
}
