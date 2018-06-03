package com.zeus.boot.controller;

import com.zeus.boot.commons.message.ResponseMessage;
import com.zeus.boot.commons.message.ResultCode;
import com.zeus.boot.entity.Board;
import com.zeus.boot.service.impl.WeChatServiceImpl;
import com.zeus.boot.vo.OrgDetails;
import com.zeus.boot.vo.OrgInfo;
import com.zeus.boot.vo.WinBoard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Web Api", tags = {"wechat接口调用"})
@RestController
@RequestMapping(path = "/wechat")
public class WeChatController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeChatServiceImpl weChatService;

    @ApiOperation(value = "查询名人(专家)胜率信息", notes = "wechat相关api")
    @GetMapping(path = "/qry/orgInfo/{id}")
    private ResponseMessage<Object> getOrgInfoByOrgType(@PathVariable("id") String orgType) {
        try {
            List<OrgInfo> orgInfos = weChatService.getOrgInfosByOrgType(orgType);
            return ResponseMessage.ok(orgInfos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /qry/orgInfo ] 内部错误");
        }
    }

    @ApiOperation(value = "根据姓名搜索名人(专家)胜率信息", notes = "wechat相关api")
    @PostMapping(path = "/qry/orgInfoByName")
    private ResponseMessage<Object> getOrgInfosByOrgTypeAndOrgName(@RequestBody Map<String, String> rcm) {
        String orgType = rcm.get("orgType");
        String orgName = rcm.get("orgName");
        try {
            List<OrgInfo> orgInfos = weChatService.getOrgInfosByOrgName(orgType,orgName);
            return ResponseMessage.ok(orgInfos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /qry/orgInfoByName ] 内部错误");
        }
    }

    @ApiOperation(value = "根据关键字搜索名人(专家)胜率信息", notes = "wechat相关api")
    @PostMapping(path = "/qry/orgInfoByKeyword")
    private ResponseMessage<Object> getOrgInfosByOrgTypeAndKeyword(@RequestBody Map<String, String> rcm) {
        String orgType = rcm.get("orgType");
        String keyWord = rcm.get("keyWord");
        try {
            List<OrgInfo> orgInfos = weChatService.getOrgInfosByKeyword(orgType,keyWord);
            return ResponseMessage.ok(orgInfos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /qry/orgInfoByKeyword ] 内部错误");
        }
    }

    @ApiOperation(value = "查询机构详细信息", notes = "wechat相关api")
    @PostMapping(path = "/qry/orgDetails")
    private ResponseMessage<Object> getOrgDetails(@RequestBody Map<String, String> rcm) {
        String orgId = rcm.get("orgId");
        try {
            OrgDetails orgDetails = weChatService.getOrgDetails(orgId);
            return ResponseMessage.ok(orgDetails);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /qry/orgDetails ] 内部错误");
        }
    }

    @ApiOperation(value = "查询连红榜", notes = "wechat相关api")
    @GetMapping(path = "/qry/continueWin")
    private ResponseMessage<Object> getContinueWin() {
        try {
            List<WinBoard> continueWin = weChatService.getContinueWin();
            return ResponseMessage.ok(continueWin);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /qry/continueWin ] 内部错误");
        }
    }

    @ApiOperation(value = "公告查询", notes = "wechat相关api")
    @GetMapping(path = "/qry/boards")
    private ResponseMessage<Object> getBoards() {
        try {
            List<Board> boards = weChatService.getBoards();
            return ResponseMessage.ok(boards);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /qry/boards ] 内部错误");
        }
    }
}
