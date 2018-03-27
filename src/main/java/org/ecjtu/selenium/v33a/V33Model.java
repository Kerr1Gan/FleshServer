package org.ecjtu.selenium.v33a;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class V33Model implements Serializable {
    private String baseUrl;
    private String imageUrl;
    private String title;
    private List<String> others;
    private String videoUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public static Map<String, List<V33Model>> getMapFromJson(String json) {
        JsonArray jArray = new Gson().fromJson(json, JsonArray.class);
        Map<String, List<V33Model>> map = new LinkedHashMap<>();
        for (int i = 0; i < jArray.size(); i++) {
            JsonObject jObj = jArray.get(i).getAsJsonObject();
            String title = jObj.get("title").getAsString();
            List<V33Model> v33List = new ArrayList<>();
            JsonArray jList = jObj.get("list").getAsJsonArray();
            for (int j = 0; j < jList.size(); j++) {
                JsonObject jListItem = jList.get(j).getAsJsonObject();
                String baseUrl = jListItem.get("baseUrl").getAsString();
                String videoUrl = jListItem.get("videoUrl").getAsString();
                String itemTitle = jListItem.get("title").getAsString();
                String imageUrl = jListItem.get("imageUrl").getAsString();

                V33Model v33Model = new V33Model();
                v33Model.setBaseUrl(baseUrl);
                v33Model.setImageUrl(imageUrl);
                v33Model.setTitle(itemTitle);
                v33Model.setVideoUrl(videoUrl);
                v33List.add(v33Model);
            }
            map.put(title, v33List);
        }
        return map;
    }
}
