package org.qmbupt.grp105.Controller;

import org.junit.Before;
import org.junit.Test;
import org.qmbupt.grp105.Entity.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LiveControllerTest {

    LiveController liveController;

    @Before
    public void setUp() throws Exception {
        liveController = LiveController.getController();
    }

    @Test
    public void getAllLiveSessions() {
        ArrayList<LiveSession> liveSessions = new ArrayList<>();
        liveSessions = liveController.getAllLiveSessions();
        for (LiveSession liveSession : liveSessions) {
            System.out.println(liveSession.toString());
        }
    }

    @Test
    public void getLiveSessionBySessionId() {
        LiveSession liveSession = liveController.getLiveSessionBySessionId("L001");
        System.out.println(liveSession.toString());
    }
}

//    @Test
//    public void getLiveSessoinsByCategory() {
//        ArrayList<LiveSession> liveSessions = new ArrayList<>();
//        liveSessions = liveController.getLiveSessoinsByCategory("Yoga");
//        for(LiveSession liveSession : liveSessions) {
//            System.out.println(liveSession.toString());
//        }
//
//    }

//    @Test
//    public void getAvailableNumOfSession() {
//        int num = liveController.getAvailableNumOfSession("L001");
//        System.out.println(num);
//    }
//
//    @Test
//    public void bookLiveSession() {
//        boolean status = liveController.bookLiveSession("C001","L001");
//        System.out.println(status);
//    }
//}