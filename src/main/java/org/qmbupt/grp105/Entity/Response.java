package org.qmbupt.grp105.Entity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.util.*;
public class Response {
    String status;
    JsonObject payload;
    public Response(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        this.status = jsonObject.get("status").getAsString();
        this.payload = jsonObject.getAsJsonObject("payload");

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JsonObject getPayload() {
        return payload;
    }

    public void setPayload(JsonObject payload) {
        this.payload = payload;
    }
}
