package com.zeus.boot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/* Hypermedia API HATEOAS */
@RestController(value = "/api")
public class ApiController {

    @Value("${web.url}")
    private String url;

    @GetMapping()
    private String getApi(){
        Map<String, Object> resful = new HashMap<>();

        // 组织机构
        resful.put("organizations_url", new StringBuilder(url).append("/org"));
        // 推荐表
        resful.put("recommend_url", new StringBuilder(url).append("/rcm"));
        // 公告
        resful.put("board_url", new StringBuilder(url).append("/brd"));

        String jsonString = JSONObject.toJSONString(resful);

        return jsonString;
    }
}
