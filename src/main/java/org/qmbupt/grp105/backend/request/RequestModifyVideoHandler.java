package org.qmbupt.grp105.backend.request;


import com.alibaba.fastjson.*;

public class RequestModifyVideoHandler implements RequestHandler {


    @Override
    public String execute(String payload) {
        
        JSONObject retJson = new JSONObject();

        try {

            JSONArray videos = JSON.parseArray(IO.read("video.json"));

            JSONObject newVideoFromPayload = JSON.parseObject(payload);
            
            for (int i = 0 ; i < videos.size(); i++) {
                if (videos.getJSONObject(i).getString("videoId").equals(newVideoFromPayload.getString("videoId"))) {
                    videos.remove(i);
                    JSONObject newVideo = new JSONObject();

                    newVideo.put("videoId", newVideoFromPayload.getString("videoId"));
                    newVideo.put("url", newVideoFromPayload.getString("url"));
                    newVideo.put("name", newVideoFromPayload.getString("name"));
                    newVideo.put("rating", Double.parseDouble(newVideoFromPayload.getString("rating")));
                    newVideo.put("likes", Integer.parseInt(newVideoFromPayload.getString("likes")));
                    newVideo.put("category", newVideoFromPayload.getString("category"));
                    newVideo.put("viewCounts", Integer.parseInt(newVideoFromPayload.getString("viewCounts")));
                    newVideo.put("level", newVideoFromPayload.getString("level"));
    
                    
                    videos.add(newVideo);
                    IO.write("video.json", videos.toJSONString());
                    break;
                }
            }
            
            retJson.put("status", "success");

        } catch (Exception ex) {
            ex.printStackTrace();
            retJson.put("status", "failed");
        }
        // TODO Auto-generated method stub
        return retJson.toJSONString();
    }
    
    
}
