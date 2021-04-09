package org.qmbupt.grp105.Entity;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String header;
    private Map<String, Object> param;
    public Request() {
        this.header = "";
        this.param = new HashMap<String, Object>();
    }
    public Request(String header, Map<String, Object>map) {
        this.header = header;
        this.param = map;
    }
    public Request(String header) {
        this.header = header;
        this.param = new HashMap<String, Object>();
    }

    public String getHeader() {
        return header;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }


    public String toJsonString() {
        String str = "{\"request\":\"" + this.header + "\",\"payload\":{";

        StringBuilder sb = new StringBuilder();
        if(param.size() > 0) {
            for(Map.Entry<String, Object> payload : param.entrySet()) {
                sb.append("\"");
                sb.append(payload.getKey());
                sb.append("\":\"");
                sb.append(payload.getValue());
                sb.append("\"");
                sb.append(",");
            }

            sb.replace(sb.length() - 1,sb.length(), "}}");
        }
        else {
            str = str + "}}";
            return str;
        }
        str += sb.toString();
        return str;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request='" + header + '\'' +
                ", payload=" + param +
                '}';
    }
}
