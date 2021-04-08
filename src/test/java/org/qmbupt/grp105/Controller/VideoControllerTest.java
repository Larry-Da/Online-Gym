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
        ArrayList<Video> videos = videoController.getAllVideos();
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
        Video video = new Video("V098","usr/local","Sdwerrtyry",8.7,"Yoga",500,1000,"easy");
        boolean status = videoController.AddVideo(video);
        System.out.println(status);
    }

    @Test
    public void modifyVideo() {
        Video video = new Video("V014","usr/local","456sl",8.7,"Yoga",700,1000,"easy");
        boolean status = videoController.modifyVideo(video);
        System.out.println(status);
    }

    @Test
    public void getVideosByCategory() {
        ArrayList<Video> videos = videoController.getVideosByCategory("Yoga");
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
        boolean status = videoController.addVideoToFavourite("Cs13","V001");
        System.out.println(status);
    }

    @Test
    public void commentOnVideo() {
        boolean status = videoController.commentOnVideo("垃圾","Cs13","V001");
        System.out.println(status);
    }

    @Test
    public void getHotVideo() {
        ArrayList<Video> videos = videoController.getHotVideo(10);
        for (Video video : videos) {
            System.out.println(video.toString());
        }
    }

    @Test
    public void getVideosByCusId() {
        ArrayList<Video> videos = videoController.getVideosByCusId("Cs15");
        for(Video video : videos) {
            System.out.println(video.toString());
        }
    }
}