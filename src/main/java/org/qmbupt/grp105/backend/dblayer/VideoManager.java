package org.qmbupt.grp105.backend.dblayer;

import java.io.IOException;
import java.util.ArrayList;

import org.qmbupt.grp105.backend.model.*;

/**
 * manage videos
 * @author Lingsong Feng
 * @version 5.3
 */
public class VideoManager {

    /**
     * write (add and modify) video information into disk
     * @param video video
     * @throws IOException IOException
     */
    public static void writeVideoInfo(Video video) throws IOException {
        writeOrDeleteVideo(video, null);
    }

    /**
     * delete an video from disk
     * @param videoId videoId
     * @throws IOException  IOException
     */
    public static void deleteVideo(String videoId) throws IOException {
        writeOrDeleteVideo(null, videoId);
    }

    /**
     * get an video by its id
     * @param videoId videoId
     * @return the reference of that Video object
     * @throws IOException IOException
     */
    public static Video getVideoById(String videoId) throws IOException {
        ArrayList<Video> videos = DataManager.getInstance().videos;
        for (Video video : videos) {
            if (video.videoId.equals(videoId)) {
                return video;
            }
        }
        return null;
    }

    /**
     * get all videos
     * @return an array of videos
     * @throws IOException IOException
     */
    public static ArrayList<Video> getVideos() throws IOException {
        return DataManager.getInstance().videos;
    }
    
    /**
     * write or delete video
     * @param video video
     * @param videoId videoId
     * @throws IOException IOException
     */
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
