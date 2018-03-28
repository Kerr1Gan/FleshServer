package com.shaw.fleshServer.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shaw.fleshServer.base.common.FleshResult;
import com.shaw.fleshServer.utils.ResourceUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/api")
public class VideoController {

    private JsonArray jV33JsonArray;

    @RequestMapping(value = "/getVideoList", method = {RequestMethod.GET, RequestMethod.POST})
    public FleshResult getVideoList(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "web", defaultValue = "v33") String web,
                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false, defaultValue = "0") Integer index,
                                    @RequestParam(required = false, defaultValue = "50") Integer length) {
        try {
            Method method = getClass().getMethod(web, int.class, int.class, int.class);
            Object ret = method.invoke(this, page, index, length);
            return new FleshResult("0", "", ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FleshResult("-1", "");
    }

    public Object v33(int page, int index, int length) {
        String path = ResourceUtil.getResourcePath(); // servletRequest.getRealPath("");同样能获取到基路径
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.out.println("VideoController v33 array " + (jV33JsonArray != null ? this.toString() : "null"));
        if (jV33JsonArray == null) {
            try (FileInputStream reader = new FileInputStream(new File(path, "v33a.json"))) {
                byte[] temp = new byte[1024 * 10];
                int len;
                while ((len = reader.read(temp, 0, temp.length)) > 0) {
                    os.write(temp, 0, len);
                }
                JsonArray jArray = new Gson().fromJson(os.toString("utf-8"), JsonArray.class);
                JsonArray ret = new JsonArray();
                if (page >= 0) {
                    JsonObject jPage = jArray.get(page).getAsJsonObject();
                    String title = jPage.get("title").getAsString();
                    JsonArray jList = jPage.get("list").getAsJsonArray();

                    JsonArray retArray = new JsonArray();
                    for (int i = index; i < index + length && i < jList.size(); i++) {
                        retArray.add(jList.get(i).getAsJsonObject());
                    }
                    JsonObject item = new JsonObject();
                    item.addProperty("title", title);
                    item.add("list", retArray);
                    ret.add(item);
                } else {
                    for (int j = 0; j < jArray.size(); j++) {
                        JsonObject jPage = jArray.get(j).getAsJsonObject();
                        String title = jPage.get("title").getAsString();
                        JsonArray jList = jPage.get("list").getAsJsonArray();

                        JsonArray retArray = new JsonArray();
                        for (int i = index; i < length && i < jList.size(); i++) {
                            retArray.add(jList.get(i).getAsJsonObject());
                        }
                        JsonObject item = new JsonObject();
                        item.addProperty("title", title);
                        item.add("list", retArray);
                        ret.add(item);
                    }
                }
                jV33JsonArray = jArray;
                return ret.toString();
            } catch (Exception e) {
                e.printStackTrace();
                jV33JsonArray = null;
            } finally {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        } else {
            JsonArray jArray = jV33JsonArray;
            JsonArray ret = new JsonArray();
            if (page >= 0) {
                JsonObject jPage = jArray.get(page).getAsJsonObject();
                String title = jPage.get("title").getAsString();
                JsonArray jList = jPage.get("list").getAsJsonArray();

                JsonArray retArray = new JsonArray();
                for (int i = index; i < index + length && i < jList.size(); i++) {
                    retArray.add(jList.get(i).getAsJsonObject());
                }
                JsonObject item = new JsonObject();
                item.addProperty("title", title);
                item.add("list", retArray);
                ret.add(item);
            } else {
                for (int j = 0; j < jArray.size(); j++) {
                    JsonObject jPage = jArray.get(j).getAsJsonObject();
                    String title = jPage.get("title").getAsString();
                    JsonArray jList = jPage.get("list").getAsJsonArray();

                    JsonArray retArray = new JsonArray();
                    for (int i = index; i < length && i < jList.size(); i++) {
                        retArray.add(jList.get(i).getAsJsonObject());
                    }
                    JsonObject item = new JsonObject();
                    item.addProperty("title", title);
                    item.add("list", retArray);
                    ret.add(item);
                }
            }
            return ret.toString();
        }
        return null;
    }
}
