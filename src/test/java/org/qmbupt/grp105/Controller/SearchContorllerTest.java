package org.qmbupt.grp105.Controller;

import org.qmbupt.grp105.Entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class SearchContorllerTest {
    SearchController searchController;
    ArrayList<Video> videos;

    @Before
    public void setUp() {
        searchController = new SearchController();
        videos = new ArrayList<>();
        videos.add(new Video("1", "12345", "video1", 3.5, "yoga", 1300, 3500,"easy"));
        videos.add(new Video("2", "12345", "video2", 3.2, "yoga", 800, 1900,"difficult"));
        videos.add(new Video("3", "12345", "video3", 3.4, "yoga", 1000, 3200,"medium"));
        videos.add(new Video("4", "12345", "video4", 3.7, "yoga", 1800, 4500,"easy"));
    }

    @Test
    public void sortVideoByRating() {
        videos = searchController.sortVideoByRating(videos);
        int length = videos.size();
        for (Video video : videos) {
            System.out.print(video.getVideoId() + ",");
        }
    }

    @Test
    public void sortVideoByLikes() {
        videos = searchController.sortVideoByLikes(videos);
        int length = videos.size();
        for (Video video : videos) {
            System.out.print(video.getVideoId() + ",");
        }
    }

    @Test
    public void updateVideo() {
        System.out.println("begin to test updateVideo()");
        ArrayList<Video> videos;
        videos = searchController.updateVideo();
        System.out.println("All sessions booked by Customer C001 are as follows");
        for (Video video : videos) {
            System.out.println(videos.toString());
        }
        System.out.println("test for updateVideo() end");
        System.out.println();
    }

    @Test
    public void getVideoByID() {
        System.out.println("begin to test getVideoByID()");
        Video video;
        video = searchController.getVideoByID("V001");
        System.out.println(video.toString());
        System.out.println("test for getVideoByID() end");
        System.out.println();
    }

    @Test
    public void getVideoByName() {
        System.out.println("begin to test getVideoByName()");
        Video video;
        video = searchController.getVideoByID("goteng");
        System.out.println(video.toString());
        System.out.println("test for getVideoByName() end");
        System.out.println();
    }

    @Test
    public void getVideoByCategory() {
        System.out.println("begin to test getVideoByCategory()");
        Video video;
        video = searchController.getVideoByID("Yoga");
        System.out.println(video.toString());
        System.out.println("test for getVideoByCategory() end");
        System.out.println("");
    }

}
