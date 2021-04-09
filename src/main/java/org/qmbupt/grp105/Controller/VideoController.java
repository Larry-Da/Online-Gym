package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.qmbupt.grp105.backend.BackendServer;
import java.util.*;

public class VideoController {
    private static Map<String, Object> param = new HashMap<>();
    private static Gson gson = new Gson();
    private static Request request;
    private static Response response;
    private static BackendServer backendServer;
    public VideoController() {};

//    public ArrayList<Video> getAllVideos() {
////        param.put("type","Video");
////        param.put("field", new String[]{"videoId"});
//        ArrayList<Video> videos = new ArrayList<>();
//        request = new Request("getVideoIds",param);
//        response = new Response(backendServer.execute(request.toJsonString()));
////        response = new Response("{\"status\":\"success\",\"payload\":{\"videoIds\":[\"V001\",\"V002\"]}}");
//        String status = response.getStatus();
//        if(status.equalsIgnoreCase("success")) {
//            JsonArray jsonArray = response.getPayload().getAsJsonArray("videoIds");
//            List<String> videoIds = gson.fromJson(jsonArray, List.class);
//            for(String videoId : videoIds) {
//                Video video = getVideoByVideoId(videoId);
//                videos.add(video);
//            }
//            param.clear();
//            return videos;
//        }
//        param.clear();
//        return null;
//    }
//    public Video getVideoByVideoId(String videoId) {
////        param.put("type","Video");
////        param.put("id",videoId);
////        param.put("fields",Video.getAllAttributes());
//        param.put("videoId",videoId);
//        request = new Request("getVideoById",param);
//        response = new Response(backendServer.execute(request.toJsonString()));
////        response = new Response("{\"status\":\"success\",\"payload\":{\"videoId\":\"V001\",\"url\":\"usr/local/bin\",\"name\":\"strength\",\"rating\":7.8,\"category\":\"Yoga\",\"likes\":100,\"viewCounts\":3000,\"level\":\"easy\"}}");
//        String status = response.getStatus();
//        if(status.equalsIgnoreCase("success")) {
//            Video video = gson.fromJson(response.getPayload(), Video.class);
//            param.clear();
//            return video;
//        }
//        param.clear();
//        return null;
//    }
//    public Video getVideoByName(String videoName) {
//        ArrayList<Video> videos = getAllVideos();
//        for(Video video : videos) {
//            if(video.getName().equalsIgnoreCase(videoName)) {
//                return video;
//            }
//        }
//        return null;
//    }
    /**
     *<p>
     *     This function gets all videos which a given customer has watched before
     *</p>
     * @param cusId customer ID
     * @return a list of videos
     */
//    public ArrayList<Video> getVideosByCusId(String cusId) {
//        PersonalController personalController = new PersonalController();
//        Customer customer = personalController.getCusInfoByCusId(cusId);
//        ArrayList<String> videoIds = customer.getVideosHistory();
//        ArrayList<Video> videos = new ArrayList<>();
//        for(String videoId : videoIds) {
//            Video video = getVideoByVideoId(videoId);
//            videos.add(video);
//        }
//        return videos;
//    }
    /**
     * <p>
     *     This function adds a new video
     * </p>
     * @param video a video entity
     * @return return true if add successfully, otherwise return false
     */
    public boolean AddVideo(Video video) {
        String json = gson.toJson(video);
        param = gson.fromJson(json, Map.class);
        request = new Request("addVideo",param);
        Double likes = (Double) param.get("likes");
        Double viewCounts = (Double)param.get("viewsCount");
        param.replace("likes",likes.intValue());
        param.replace("viewsCount",viewCounts.intValue());
        response = new Response(backendServer.execute(request.toJsonString()));
//        response = new Response("{\"status\":\"success\"}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            param.clear();
            return true;
        }
        param.clear();
        return false;
    }

    /**
     * <p>
     *     This function modifies a given video
     * </p>
     * @param video a modified video entity
     * @return  return true if modification works, otherwise return false
     */
    public boolean modifyVideo(Video video) {
        String json = gson.toJson(video);
        param = gson.fromJson(json, Map.class);
        Double likes = (Double) param.get("likes");
        Double viewCounts = (Double)param.get("viewsCount");
        param.replace("likes",likes.intValue());
        param.replace("viewsCount",viewCounts.intValue());
        request = new Request("modifyVideo",param);
        response = new Response(backendServer.execute(request.toJsonString()));
//        response = new Response("{\"status\":\"success\"}");
        String status = response.getStatus();
        if(status.equalsIgnoreCase("success")) {
            param.clear();
            return true;
        }
        param.clear();
        return false;
    }
//    public ArrayList<Video> getVideosByCategory(String category) {
//        ArrayList<Video> videos = getAllVideos();
//        ArrayList<Video> videoList = new ArrayList<>();
//        for(Video video : videos) {
//            if(video.getCategory().equalsIgnoreCase(category)) {
//                videoList.add(video);
//            }
//        }
//        return videoList;
//    }
//    public boolean likeVideo(String videoId) {
//        param.put("videoId",videoId);
//        request = new Request("liekeVideo",param);
//        //        response = new Response(backend.likeVideo(request));
//        response = new Response("{\"status\":\"success\"}");
//        String status = response.getStatus();
//        if(status.equalsIgnoreCase("success")) {
//            param.clear();
//            return true;
//        }
//        param.clear();
//        return false;
//    }
//    public boolean addVideoToFavourite(String cusId, String videoId) {
//        param.put("videoId",videoId);
//        param.put("cusId",cusId);
//        request = new Request("addVideoToFavourite",param);
//        //        response = new Response(backend.addVideoToFavourite(request));
//        response = new Response("{\"status\":\"success\"}");
//        String status = response.getStatus();
//        if(status.equalsIgnoreCase("success")) {
//            param.clear();
//            return true;
//        }
//        param.clear();
//        return false;
//    }
//    public boolean commentOnVideo(String comment, String cusId, String videoId) {
//        param.put("comment",comment);
//        param.put("cusId",cusId);
//        param.put("videoId",videoId);
//        request = new Request("commentOnVideo",param);
//        //        response = new Response(backend.addVideoToFavourite(request));
//        response = new Response("{\"status\":\"success\"}");
//        String status = response.getStatus();
//        if(status.equalsIgnoreCase("success")) {
//            param.clear();
//            return true;
//        }
//        param.clear();
//        return false;
//    }
//    public ArrayList<Video> getHotVideo(int threshold) {
//        ArrayList<Video> videos = getAllVideos();
//        ArrayList<Video> hotVideos = new ArrayList<>();
//        for(Video video : videos) {
//            if(video.getViewsCount() >= threshold) {
//                hotVideos.add(video);
//            }
//        }
//        Collections.sort(hotVideos, new Comparator<Video>() {
//            @Override
//            public int compare(Video o1, Video o2) {
//                return o2.getViewsCount() - o1.getViewsCount();
//            }
//        });
//        return hotVideos;
//    }
}
