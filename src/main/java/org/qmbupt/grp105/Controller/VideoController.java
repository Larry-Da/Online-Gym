package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.Customer;
import org.qmbupt.grp105.Entity.Video;
import org.qmbupt.grp105.backend.dblayer.*;
import org.qmbupt.grp105.backend.dblayer.VideoManager;
import java.io.IOException;
import java.util.*;

public class VideoController {

    private static VideoController videoController = new VideoController();
    private VideoController() {};
    public static VideoController getController()
    {
        return videoController;
    }




    public ArrayList<Video> getAllVideos() {
        ArrayList<Video> videos = new ArrayList<>();

        try {
            ArrayList<org.qmbupt.grp105.backend.model.Video> temp = VideoManager.getVideos();
            for(org.qmbupt.grp105.backend.model.Video i : temp)
            {
                videos.add(i.converter());
            }
        }
        catch(Exception e)
        {

        }
        return videos;
    }
    public Video getVideoByVideoId(String videoId) {
        Video video = null;
        try {
            video = VideoManager.getVideoById(videoId).converter();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return video;

    }
    public ArrayList<Video> sort(ArrayList<Video> videos, String param) {
        if(param == null)
            return videos;
        if(param.equals("View")) {
            Collections.sort(videos, new Comparator<Video>() {
                @Override
                public int compare(Video o1, Video o2) {
                    return o2.getViewsCount() - o1.getViewsCount();
                }
            });
        }
        else if(param.equals("Rating")) {
            Collections.sort(videos, new Comparator<Video>() {
                @Override
                public int compare(Video o1, Video o2) {
                    if(o2.getRating() - o1.getRating() > 0)
                        return 1;
                    else if(o2.getRating() == o1.getRating())
                        return 0;
                    else
                        return -1;
                }
            });
        }
        else if(param.equals("Like")) {
            Collections.sort(videos, new Comparator<Video>() {
                @Override
                public int compare(Video o1, Video o2) {
                    return o1.getLikes() - o2.getLikes();
                }
            });
        }
        return videos;
    }
    public Video getVideoByName(String videoName) {
        ArrayList<Video> videos = getAllVideos();
        for(Video video : videos) {
            if(video.getName().equalsIgnoreCase(videoName)) {
                return video;
            }
        }
        return null;
    }

    public ArrayList<Video> getVideosByName(String keyword) {
        ArrayList<Video> videos = getAllVideos();
        if(keyword == null)
            return videos;
        ArrayList<Video> res = new ArrayList<>();
        for(Video v: videos) {
            if(v.getName().contains(keyword)) {
                res.add(v);
            }
        }
        return res;
    }

    public ArrayList<Video> filterByCategory(ArrayList<Video> videos, List<String> category) {
        ArrayList<Video> videos1 = null;
        if(videos == null) {
            videos1 = getAllVideos();
        }
        else {
            videos1 = videos;
        }
        if(category.size() == 0) // if no category chosen
            return videos1;
        ArrayList<Video> res = new ArrayList<>();
        for(Video v: videos1) {
            if(category.contains(v.getCategory())) {
                res.add(v);
            }
        }
        return res;
    }
    /**
     *<p>
     *     This function gets all videos which a given customer has watched before
     *</p>
     * @param cusId customer ID
     * @return a list of videos
     */
    public ArrayList<Video> getVideosByCusId(String cusId) {
        PersonalController personalController = PersonalController.getController();
        Customer customer = personalController.getCusInfoByCusId(cusId);
        ArrayList<String> videoIds = customer.getVideosHistory();
        ArrayList<Video> videos = new ArrayList<>();
        for(String videoId : videoIds) {
            Video video = getVideoByVideoId(videoId);
            videos.add(video);
        }
        return videos;
    }
    /**
     * <p>
     *     This function adds a new video
     * </p>
     * @param video a video entity
     * @return return true if add successfully, otherwise return false
     */
    public void AddVideo(Video video) {
        int num = getAllVideos().size();
        String videoId = String.format("%03d", num + 1);
        video.setVideoId(videoId);
        try {
            VideoManager.writeVideoInfo(video.convert());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete video by videoId
     * @param videoId
     */
    public void deleteVideo(String videoId) {
        try {
            VideoManager.deleteVideo(videoId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>
     *     This function modifies a given video
     * </p>
     * @param video a modified video entity
     * @return  return true if modification works, otherwise return false
     */
    public void modifyVideo(Video video) {
        try {
            VideoManager.writeVideoInfo(video.convert());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public ArrayList<Video> getHotVideo(int threshold) {
        ArrayList<Video> videos = getAllVideos();
        ArrayList<Video> hotVideos = new ArrayList<>();
        for(Video video : videos) {
            if(video.getViewsCount() >= threshold) {
                hotVideos.add(video);
            }
        }
        Collections.sort(hotVideos, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                return o2.getViewsCount() - o1.getViewsCount();
            }
        });
        return hotVideos;
    }
    public String[] getCategories()
    {
        ArrayList<String> res = null;
        try
        {
            res = SettingsManager.getSetting("category");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<String> temp = new ArrayList<String>(res);
        temp.add(0, "Category");
        if(temp.size() > 7)
        {
            temp.add(1, "All");
        }
        String[] ret = new String[temp.size()];
        return temp.toArray(ret);
    }
    public String[] getMemberships() {
        {
            ArrayList<String> res = null;
            try {
                res = SettingsManager.getSetting("membership");
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<String> temp = new ArrayList<String>(res);
            temp.add(0, "Level");
            if(temp.size() > 7)
            {
                temp.add(1, "All");
            }
            String[] ret = new String[temp.size()];
            return temp.toArray(ret);
        }
    }
}
