package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;
import java.util.ArrayList;

import com.alibaba.fastjson.*;
import org.qmbupt.grp105.backend.model.*;

public class VideoManager {

    public static void writeVideoInfo(Video video) throws IOException {
        writeOrDeleteVideo(video, null);
    }

    public static void deleteVideo(String videoId) throws IOException {
        writeOrDeleteVideo(null, videoId);
    }

    public static Video getVideoById(String videoId) throws IOException {
        ArrayList<Video> videos = DataManager.getInstance().videos;
        for (Video video : videos) {
            if (video.videoId.equals(videoId)) {
                return video;
            }
        }
        return null;
    }

    public static ArrayList<Video> getVideos() throws IOException {
        return DataManager.getInstance().videos;
    }

    public static ArrayList<String> getVideoIds() throws IOException {
        ArrayList<Video> videos = DataManager.getInstance().videos;

        ArrayList<String> ret = new ArrayList<>();

        for (Video video : videos) {
            ret.add(video.videoId);
        }

        return ret;
    }

    private static void writeOrDeleteVideo(Video video, String videoId) throws IOException {
        if (video != null) videoId = video.videoId;

        ArrayList<Video> videos = DataManager.getInstance().videos;
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).videoId.equals(videoId)) {
                videos.remove(i);
                break;
            }
        }

        if (video != null) videos.add(video);

        DataManager.getInstance().commit();
    }
    
}
