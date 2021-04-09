package org.qmbupt.grp105.backend;

public class Test {
    public static void main(String[] args) {

        System.out.println(BackendServer.execute("{\"request\": \"getSessionIdsByCusId\",\"payload\":{\"cusId\": \"Cs13\"}}"));

        
        // 1.1
        System.out.println("1.1\n" + BackendServer.execute("{\"request\": \"getCustomerIds\",\"payload\":{}}"));
        // 1.2
        System.out.println("1.2\n" + BackendServer.execute("{\"request\": \"getCustomerById\",\"payload\":{\"cusId\": \"Cs13\"}}"));
        // 1.3
        System.out.println("1.3\n" + BackendServer.execute("{\"request\":\"getCusIdByName\",\"payload\":{\"cusName\":\"Meredith Grey\"}}"));
        // 1.4
        System.out.println("1.4\n" + BackendServer.execute("{\"request\":\"getCusIdsByGender\",\"payload\":{\"gender\":\"M\"}}"));
        // 1.5
        System.out.println("1.5\n" + BackendServer.execute("{\"request\":\"getExpireTimeByCusId\",\"payload\":{\"cusId\":\"Cs15\"}}"));

        // 2.1
        System.out.println("2.1\n" + BackendServer.execute("{\"request\":\"getCusNumByLevel\",\"payload\":{\"membershipLevel\":\"L0\"}}"));

        // 3.1
        System.out.println("3.1\n" + BackendServer.execute("{\"request\":\"getMonthlyIncome\",\"payload\":{\"month\":\"02\"}}"));

        // 4.1
        System.out.println("4.1\n" + BackendServer.execute("{\"request\":\"getVideoIdsByCusId\",\"payload\":{\"cusId\":\"Cs15\"}}"));
        // 4.2
        System.out.println("4.2\n" + BackendServer.execute("{\"request\": \"addVideo\",\"payload\": {\"videoId\": \"V123\",\"url\": \"usr/local/bin\",\"name\": \"strength\",\"rating\": \"7.8\",\"category\": \"Yoga\",\"likes\": \"100\",\"viewCounts\": \"3000\",\"level\": \"easy\"}}"));
        // 4.3
        System.out.println("4.3\n" + BackendServer.execute("{\"request\": \"modifyVideo\",\"payload\": {\"videoId\": \"V001\",\"url\": \"usr/local/bin\",\"name\": \"strength\",\"rating\": \"7.8\",\"category\": \"Yoga\",\"likes\": \"200\",\"viewCounts\": \"3000\",\"level\": \"easy\"}}"));
    }
}