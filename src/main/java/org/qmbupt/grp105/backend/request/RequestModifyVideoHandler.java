package org.qmbupt.grp105.backend.request;

public class RequestModifyVideoHandler implements RequestHandler {


    @Override
    public String execute(String payload) {
        RequestAddVideoHandler rh = new RequestAddVideoHandler();
        return rh.execute(payload);
    }
    
    
}
