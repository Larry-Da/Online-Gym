package Controller;

import java.util.Comparator;
import java.util.Collections;

import Entity.Customer;
import Entity.Request;
import Entity.Response;
import Entity.Video;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.*;

public class SearchController extends Request {
    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;

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
        response = new Response("{\"status\":\"success\",\"payload\":{\"videoIds\":[\"V001\",\"V002\"]}}");
        String status = response.getStatus();
        if (status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("videoIds");
            List<String> videoIds = gson.fromJson(jsonArray, List.class);
            for (String videoId : videoIds) {
                Video video = getVideoByID(videoId);
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
    public Video getVideoByID(String videoID) {
        param.put("videoID", videoID);
        request = new Request("getVideoByID", param);
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
            Video video = gson.fromJson(response.getPayload(), Video.class);
            param.clear();
            return video;
        }
        return null;
    }

    /**
     * <p>
     * This function gets the video by its name
     * </p>
     *
     * @param videoName video's name
     * @return a video entity
     */
    public Video getVideoByName(String videoName) {
        param.put("videoName", videoName);
        request = new Request("getVideoByName", param);
//        response = new Response(backend.getVideoByName(request.toJsonString()));
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
            Video video = gson.fromJson(response.getPayload(), Video.class);
            param.clear();
            return video;
        }
        return null;
    }

    /**
     * <p>
     * This function is to get the video by its category
     * </p>
     *
     * @param category video's category
     * @return a list of videos with particular category
     */
    public ArrayList<Video> getVideoByCategory(String category) {
        ArrayList<Video> videos = new ArrayList<>();
        param.put("category", category);
        request = new Request("getVideoByCategory", param);
//        response = new Response(backend.getVideoByCategory(request.toJsonString()));
        response = new Response("{\"status\":\"success\",\"payload\":{\"videoIds\":[\"V003\",\"V005\"]}}");
        String status = response.getStatus();
        if (status.equalsIgnoreCase("success")) {
            JsonArray jsonArray = response.getPayload().getAsJsonArray("videoIds");
            List<String> videoIds = gson.fromJson(jsonArray, List.class);
            for (String videoId : videoIds) {
                Video video = getVideoByID(videoId);
                videos.add(video);
            }
            return videos;
        }
        return null;
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
        for (int i = 1; i < videos.size(); i++) {
            Video temp = videos.get(i);
            int in = i; //记录位置
            while (in > 0 && videos.get(in - 1).getRating().compareTo(temp.getRating()) < 0)//前面的比后面的大:
            {
                videos.set(in, videos.get(in - 1));//前面的元素后移
                in--;
            }
            videos.set(in, temp);
        }
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

