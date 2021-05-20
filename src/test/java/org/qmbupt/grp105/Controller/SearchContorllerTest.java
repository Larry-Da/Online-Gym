package org.qmbupt.grp105.Controller;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.qmbupt.grp105.Entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchContorllerTest {
    SearchController searchController;
    ArrayList<Video> videos;

    @Before
    public void setUp() {
        searchController = SearchController.getController();
        videos = VideoController.getController().getAllVideos();

    }

    @Test
    public void sortVideoByRating() {
        System.out.println("sortByRating");
        videos = searchController.sortVideoByRating(videos);
        int length = videos.size();
        for (Video video : videos) {
            System.out.print(video.getVideoId() + " ");
        }
        System.out.println();
    }

    @Test
    public void sortVideoByLikes() {
        System.out.println("sortByLikes");
        videos = searchController.sortVideoByLikes(videos);
        int length = videos.size();
        for (Video video : videos) {
            System.out.print(video.getVideoId() + " ");
        }
        System.out.println("");
    }
}

