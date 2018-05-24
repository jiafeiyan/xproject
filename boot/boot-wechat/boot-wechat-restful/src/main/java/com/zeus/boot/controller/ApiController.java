package com.zeus.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/* Hypermedia API HATEOAS */
@Api(value = "Hypermedia API", tags = {"统一接口列表"})
@RestController
@RequestMapping(path = "api")
public class ApiController {

    @Value("${web.url}")
    private String url;

    @ApiOperation(value="获取所有API接口", notes="显示接口URL")
    @GetMapping(path = "")
    private Map<String, Object> getApi(){
        Map<String, Object> resful = new HashMap<>();
        // 组织机构
        resful.put("organizations_url", new StringBuilder(url).append("/org"));
        // 推荐表
        resful.put("recommend_url", new StringBuilder(url).append("/rcm"));
        // 公告
        resful.put("board_url", new StringBuilder(url).append("/brd"));

        return resful;
    }
}
