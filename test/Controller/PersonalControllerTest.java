package Controller;

import Entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;
public class PersonalControllerTest {
    PersonalController personalController;
    @Before
    public void setUp() throws Exception {
        personalController = new PersonalController();
    }

    @Test
    public void getSession() {
        System.out.println("begin to test getSession()");
        ArrayList<LiveSession> sessions = new ArrayList<>();
        sessions = personalController.getSession("C001");
        System.out.println("All sessions booked by Customer C001 are as follows");
        for(LiveSession session : sessions) {
            System.out.println(session.toString());
        }
        System.out.println("test for getSession() end");
        System.out.println("");
    }

    @Test
    public void getAllCustomer() {
        System.out.println("begin to test getAllCustomer()");
        ArrayList<Customer> customers = new ArrayList<>();
        customers = personalController.getAllCustomer();
        System.out.println("All customers are as follows");
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println("test for getAllCustomer() end");
        System.out.println("");
    }

    @Test
    public void getCusInfoByCusId() {
        System.out.println("begin to test getCusInfoByCusId()");

        Customer customer = personalController.getCusInfoByCusId("C001");
        System.out.println(customer.toString());
        System.out.println("test for getACusInfoByCusId() end");
        System.out.println("");
    }

    @Test
    public void getCusInfoById() {
        System.out.println("begin to test getCusInfoById()");
        Customer customer = personalController.getCusInfoById("C001");
        System.out.println(customer.toString());
        System.out.println("test for getCusInfoById() end");
        System.out.println("");
    }

    @Test
    public void getCusInfoByName() {
        System.out.println("begin to test getCusInfoByName()");
        Customer customer = personalController.getCusInfoByName("C001");
        System.out.println(customer.toString());
        System.out.println("test for getCusInfoByName() end");
        System.out.println("");
    }

    @Test
    public void getCusByGender() {
        System.out.println("begin to test getCusInfoByGender()");
        ArrayList<Customer> customers = new ArrayList<>();
        customers = personalController.getCusByGender("M");
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println("test for getCusByGender() end");
        System.out.println("");
    }

    @Test
    public void getCusNumByLevel() {
        System.out.println("begin to test getCusNumByLevel()");
        System.out.println(personalController.getCusNumByLevel("L1"));
        System.out.println("test for getCusNumByLevel() end");
        System.out.println("");
    }

    @Test
    public void getMonthlyIncome() {
        System.out.println("begin to test getMonthlyIncome()");
        System.out.println(personalController.getMonthlyIncome("June"));
        System.out.println("test for getMonthlyIncome() end");
        System.out.println("");
    }

    @Test
    public void getExpireTimeByCusId() {
        System.out.println("begin to test getExpireTimeByCusId()");
        System.out.println(personalController.getExpireTimeByCusId("C001"));
        System.out.println("test for getExpireTimeByCusId() end");
        System.out.println("");
    }

    @Test
    public void getVideosByCusId() {
        System.out.println("begin to test getVideosByCusId()");
        ArrayList<Video> videos = new ArrayList<>();
        videos = personalController.getVideosByCusId("C001");
        for(Video video : videos) {
            System.out.println(video.toString());
        }
        System.out.println("test for getVideosByCusId() end");
        System.out.println("");
    }

    @Test
    public void addVideo() {
        System.out.println("begin to test addVideo()");
        Video video = new Video("V001","usr/local/bin","Strength",6.8,"Yoga",100,3000);
        System.out.println(personalController.AddVideo(video));
        System.out.println("test for addVideo() end");
        System.out.println("");
    }

    @Test
    public void modifyVideo() {
        System.out.println("begin to test modifyVideo()");
        Video video = new Video("V001","usr/local/bin","Strength",6.8,"Yoga",100,3000);
        System.out.println(personalController.modifyVideo(video));
        System.out.println("test for modifyVideo() end");
        System.out.println("");
    }
}