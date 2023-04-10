package com.holden.wxproject.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName wxproject-ChatGPTController
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年3月02日11:23 - 周四
 * @Describe
 */
@Api(tags = {"Chat GPT接口"})
@RestController
public class ChatGPTController {
    final String url = "https://api.openai.com/v1/chat/completions";


    @ApiOperation(value = "你的问题")
    @GetMapping("/question")
    public DataResult<Object> getSingleStock(@RequestParam("content") String content){
        try {
            // 设置 JSON 参数
            Map<String, Object> params = new HashMap<>();
            params.put("model", "gpt-3.5-turbo");

            JSONObject contents = new JSONObject();
            JSONArray objects = new JSONArray();
            contents.put("role", "user");
            contents.put("content", content);
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
            return DataResult.ok(jsonObject.getJSONArray("choices").getJSONObject(0)
                    .getJSONObject("message").getString("content"));
        } catch (Exception e) {
            return DataResult.fail("接口出错，联系肖华");
        }

    }
}
