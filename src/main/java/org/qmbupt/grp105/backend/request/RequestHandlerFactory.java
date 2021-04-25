package org.qmbupt.grp105.backend.request;

import java.util.logging.Logger;

public class RequestHandlerFactory {
    public static RequestHandler createHandler(String type) {
        if (type.equals("getSessionIdsByCusId")) {
            return new RequestGetSessionIdsByCusIdHandler();
        } else if (type.equals("getCustomerIds")) {
            return new RequestGetCustomerIdsHandler();
        } else if (type.equals("getCustomerById")) {
            return new RequestGetCustomerByIdHandler();
        } else if (type.equals("getExpireTimeByCusId")) {
            return new RequestGetExpireTimeByCusIdHandler();
        } else if (type.equals("getCusIdByName")) {
            return new RequestGetCusIdByNameHandler();
        } else if (type.equals("getCusIdsByGender")) {
            return new RequestGetCusIdsByGenderHandler();
        } else if (type.equals("getCusNumByLevel")) {
            return new RequestGetCusNumByLevelHandler();
        } else if (type.equals("getMonthlyIncome")) {
            return new RequestGetMonthlyIncomeHandler();
        } else if (type.equals("addVideo")) {
            return new RequestAddVideoHandler();
        } else if (type.equals("modifyVideo")) {
            return new RequestModifyVideoHandler();
        } else if (type.equals("getVideoIdsByCusId")) {
            return new RequestGetVideoIdsByCusIdHandler();
        }

        return null;
    }
}
