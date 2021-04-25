package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;
import java.util.ArrayList;

import com.alibaba.fastjson.*;
import org.qmbupt.grp105.backend.model.*;

public class VideoManager {
    
    private static final String JSON_FILE_NAME = "video.json";



    public static void writeVideoInfo(Video video) throws IOException {
        writeOrDeleteVideo(video, null);
    }

    public static void deleteVideo(String videoId) throws IOException {
        writeOrDeleteVideo(null, videoId);
    }

    public static ArrayList<Video> getVideos() throws IOException {

        ArrayList<Video> ret = new ArrayList<>();

        JSONArray videos = JSON.parseArray(IO.read(JSON_FILE_NAME));

        for (int i = 0; i < videos.size(); i++) {
            Video video = videos.getObject(i, Video.class);
            ret.add(video);
        }

        return ret;
    }

    public static ArrayList<String> getVideoIds() throws IOException {
        ArrayList<Video> videos = getVideos();
        ArrayList<String> ret = new ArrayList<>();

        for (int i = 0; i < videos.size(); i++) {
            ret.add(videos.get(i).videoId);
        }

        return ret;
    }

    private static void writeOrDeleteVideo(Video video, String videoId) throws IOException {

        if (video != null) videoId = video.videoId;

        JSONArray videos = JSON.parseArray(IO.read(JSON_FILE_NAME));

        for (int i = 0; i < videos.size(); i++) {
            if (videoId.equals(videos.getJSONObject(i).getString("videoId"))) {
                videos.remove(i);
                break;
            }
        }

        if (video != null) videos.add(video);
        
        IO.write(JSON_FILE_NAME, videos.toJSONString());
    }
    
}
