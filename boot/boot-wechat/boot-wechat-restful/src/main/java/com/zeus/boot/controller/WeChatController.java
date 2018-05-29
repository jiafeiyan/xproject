package com.zeus.boot.controller;

import com.zeus.boot.commons.message.ResponseMessage;
import com.zeus.boot.commons.message.ResultCode;
import com.zeus.boot.repo.BoardRepository;
import com.zeus.boot.repo.OrganizationRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.impl.WeChatServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "Web Api", tags = {"wechat接口调用"})
@RestController
@RequestMapping(path = "/wechat")
public class WeChatController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeChatServiceImpl weChatService;

    @ApiOperation(value = "查询名人(专家)胜率信息", notes = "wechat相关api")
    @GetMapping(path = "/qry/orgInfo")
    private ResponseMessage<Object> getRcmList() {
        try {
            Map<Object, Object> orgInfos =weChatService.getOrgInfos();
            return ResponseMessage.ok(orgInfos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/list ] 内部错误");
        }
    }
}
