package org.qmbupt.grp105.Controller;

import org.junit.Before;
import org.junit.Test;
import org.qmbupt.grp105.Entity.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonalControllerTest {

    PersonalController personalController;
    @Before
    public void setUp() throws Exception {
        personalController = new PersonalController();
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
    public void getCusInfoByName() {
        System.out.println("begin to test getCusInfoByName()");
        Customer customer = personalController.getCusInfoByName("goteng");
        System.out.println(customer.toString());
        System.out.println("test for getCusInfoByName() end");
        System.out.println("");
    }

    @Test
    public void getCusByGender() {
        System.out.println("begin to test getCusInfoByGender()");
        ArrayList<Customer> customers = new ArrayList<>();
        customers = personalController.getCusByGender('M');
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
        System.out.println(personalController.getMonthlyIncome(6));
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
}