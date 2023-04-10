package com.holden.wxproject;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.util.DataResult;
import okhttp3.*;
import org.apache.http.message.BasicHeader;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


class WxprojectApplicationTests {
    public static void main(String[] args) throws Exception {
        final String url = "https://api.openai.com/v1/chat/completions";

        Map<String, Object> params = new HashMap<>();
        params.put("model", "gpt-3.5-turbo");

        JSONObject contents = new JSONObject();
        JSONArray objects = new JSONArray();
        contents.put("role", "user");
        contents.put("content", "你是谁");
        objects.add(contents);
        params.put("messages", objects);
        String jsonParams = JSONUtil.toJsonStr(params);
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.parse("application/json"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Authorization", "Bearer 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")
                .addHeader("Content-Type", "application/json")
                .build();

        // 发送请求并获取响应
        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        JSONObject jsonObject = JSONArray.parseObject(responseBody);
        System.out.println(jsonObject.getJSONArray("choices").getJSONObject(0)
                .getJSONObject("message").getString("content"));


    }
}
