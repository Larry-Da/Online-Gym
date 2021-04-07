package org.qmbupt.grp105.Controller;

import org.junit.Before;
import org.junit.Test;
import org.qmbupt.grp105.Entity.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class VideoControllerTest {

    VideoController videoController;

    @Before
    public void setUp() throws Exception {
        videoController = new VideoController();
    }

    @Test
    public void getAllVideos() {
        ArrayList<Video> videos = new ArrayList<>();
        videos = videoController.getAllVideos();
        for(Video video : videos) {
            System.out.println(video.toString());
        }
    }

    @Test
    public void getVideoByVideoId() {
        Video video = videoController.getVideoByVideoId("V001");
        System.out.println(video.toString());
    }

    @Test
    public void addVideo() {
        Video video = new Video("V008","usr/local/bin","Sdfs",8.7,"Yoga",100,1000,"easy");
        boolean status = videoController.AddVideo(video);
        System.out.println(status);
    }

    @Test
    public void modifyVideo() {
        Video video = new Video("V008","usr/local/bin","sldfjslfjskfjslkdfjsl",8.7,"Yoga",100,1000,"easy");
        boolean status = videoController.AddVideo(video);
        System.out.println(status);
    }

    @Test
    public void getVideosByCategory() {
        ArrayList<Video> videos = new ArrayList<>();
        videos = videoController.getVideosByCategory("yoga");
        for(Video video : videos) {
            System.out.println(video.toString());
        }
    }

    @Test
    public void likeVideo() {
        boolean status = videoController.likeVideo("V001");
        System.out.println(status);
    }

    @Test
    public void addVideoToFavourite() {
        boolean status = videoController.addVideoToFavourite("C001","V001");
        System.out.println(status);
    }

    @Test
    public void commentOnVideo() {
        boolean status = videoController.commentOnVideo("垃圾","C001","V001");
        System.out.println(status);
    }

    @Test
    public void getHotVideo() {
        ArrayList<Video> videos = new ArrayList<>();
        videos = videoController.getHotVideo(10);
        for(Video video : videos) {
            System.out.println(video.toString());
        }
    }
}