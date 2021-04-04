package request;

public class RequestHandlerFactory {
    public static RequestHandler creatHandler(String type) {
        if (type.equals("getSessionIdsByCusId")) {
            return new RequestGetSessionIdsByCusIdHandler();
        } else if (type.equals("getCustomerIds")) {
            return new RequestGetCustomerIdsHandler();
        } else if (type.equals("getCustomerById")) {
            return new RequestGetCustomerByIdHandler();
        } else if (type.equals("getExpireTimeByCusId")) {
            return new RequestGetExpireTimeByCusIdHandler();
        }

        return null;
    }
}
