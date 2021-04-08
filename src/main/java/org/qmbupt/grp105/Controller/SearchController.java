package org.qmbupt.grp105.Controller;

import java.util.Comparator;
import java.util.Collections;

import org.qmbupt.grp105.Entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.*;

public class SearchController extends Request {
    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;
    private static VideoController videoController = new VideoController();
    public SearchController() {
    }

    /**
     * <p>
     * This function get all videos from the backend to update the memory.
     * </p>
     *
     * @return a list of all videos
     */
    public ArrayList<Video> updateVideo() {
        ArrayList<Video> videos = new ArrayList<>();
//        response = new Response(backend.updateVideo());
        response = new Response("{\"status\":\"success\",\"payload\":{\"videoIds\":[\"V001\",\"V123\"]}}");
        String status = response.getStatus();
        if (status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("videoIds");
            List<String> videoIds = gson.fromJson(jsonArray, List.class);
            for (String videoId : videoIds) {
                Video video = searchVideoByID(videoId);
                videos.add(video);
            }
            return videos;
        }
        return null;
    }

    /**
     * <p>
     * This function gets all information of a given video
     * </p>
     *
     * @param videoID video's ID
     * @return a video entity
     */
    public Video searchVideoByID(String videoID) {
        return videoController.getVideoByVideoId(videoID);
    }

    /**
     * <p>
     * This function gets the video by its name
     * </p>
     *
     * @param videoName video's name
     * @return a video entity
     */
    public Video searchVideoByName(String videoName) {
        return videoController.getVideoByName(videoName);
    }

    /**
     * <p>
     * This function is to get the video by its category
     * </p>
     *
     * @param category video's category
     * @return a list of videos with particular category
     */
    public ArrayList<Video> searchVideoByCategory(String category) {
        return videoController.getVideosByCategory(category);
    }

    /**
     * <p>
     * This function is to sort the given videos by their rating
     * </p>
     *
     * @param videos a list of videos that need to be sorted
     * @return a list of sorted videos
     */
    public ArrayList<Video> sortVideoByRating(ArrayList<Video> videos) {
        Collections.sort(videos, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                if(o2.getRating() > o1.getRating()) {
                    return 1;
                }
                return 0;
            }
        });
        return videos;
    }

    /**
     * <p>
     * This function is to sort the given videos by their number of likes
     * </p>
     *
     * @param videos a list of videos that need to be sorted.
     * @return a list of sorted videos.
     */
    public ArrayList<Video> sortVideoByLikes(ArrayList<Video> videos) {
        Collections.sort(videos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // TODO Auto-generated method stub
                Video stu1 = (Video) o1;
                Video stu2 = (Video) o2;
                return stu2.getLikes() - stu1.getLikes();
            }
        });
        return videos;
    }

    /**
     * <p>
     * This function is to delete a video by its ID
     * </p>
     *
     * @param videoID video's ID
     * @return the operation's status
     */
    public String deleteVideo(String videoID) {
        param.put("videoID", videoID);
        request = new Request("deleteVideo", param);
//        response = new Response(backend.getVideoByID(request.toJsonString()));
        response = new Response(
                "{\"status\":\"success\"," +
                        "\"payload\":" +
                        "{\"videoId\":\"V001\"," +
                        "\"url\":\"https://www.bilibili.com/video/BV1FX4y1G7aM?spm_id_from=333.851.b_7265636f6d6d656e64.1\"," +
                        "\"name\":\"goteng\"," +
                        "\"rating\":\"3.5\"," +
                        "\"category\":\"Yoga\"," +
                        "\"likes\":\"1388\"," +
                        "\"viewCounts\":\"13000\"}}");
        String status = response.getStatus();
        if (status.equalsIgnoreCase("success")) {
            return "success";
        }
        return "failure";
    }

}

