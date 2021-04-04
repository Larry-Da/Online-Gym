public class Test {
    public static void main(String[] args) {

        System.out.println(BackendServer.execute("{\"request\": \"getSessionIdsByCusId\",\"payload\":{\"cusId\": \"Cs13\"}}"));
        
        
        // 1.1
        System.out.println("1.1\n" + BackendServer.execute("{\"request\": \"getCustomerIds\",\"payload\":{}}"));
        // 1.2
        System.out.println("1.2\n" + BackendServer.execute("{\"request\": \"getCustomerById\",\"payload\":{\"cusId\": \"Cs13\"}}"));
    
        // 1.5
        System.out.println("1.5\n" + BackendServer.execute("{\"request\":\"getExpireTimeByCusId\",\"payload\":{\"cusId\":\"Cs15\"}}"));
    
    }
}