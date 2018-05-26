package com.zeus.boot.controller;

import com.zeus.boot.commons.message.ResponseMessage;
import com.zeus.boot.entity.Admin;
import com.zeus.boot.entity.Recommend;
import com.zeus.boot.repo.AdminRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.ManagerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
 * 开发规范
  * 能直接访问Repository获取结果走Repository
  * 其他包含业务逻辑等事务处理都走Service
  * */
@Api(value = "Web Api", tags = {"web后台管理系统"})
@RestController
@RequestMapping()
public class ManagerController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "管理员登陆接口")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常")})
    @PostMapping(path = "/login")
    private Map<String, Object> login(String username, String password){

        Admin admin = adminRepository.getOne(username);

        Map<String, Object> result = new HashMap<>();
        if(password.equals(admin.getPassword())){
            result.put("username", admin.getUsername());
            result.put("password", admin.getPassword());
            result.put("type", null);
        }
        return result;
    }

    @ApiOperation(value = "增加", notes = "推单相关api")
    @PutMapping(path = "/rcm/add")
    private ResponseMessage addRcm(@RequestBody Map<String, String> rcm){
        Recommend recommend = new Recommend();
        recommend.setRCM_RCMERID(rcm.get("rcmerId"));
        recommend.setRCM_RCMERNAME(rcm.get("rcmerName"));
        recommend.setRCM_RCMERTYPE(rcm.get("rcmerType"));
        recommend.setRCM_INTRODUCTION(rcm.get("rcmIntrocution"));
        recommend.setRCM_PAYFLAG(rcm.get("rcmPayFlag"));
        recommend.setRCM_DATE(rcm.get("rcmDate"));
        recommend.setRCM_TIME(rcm.get("rcmTime"));
        recommend.setRCM_CONTENT(rcm.get("rcmContent"));
        recommend.setRCM_RESULT(rcm.get("rcmResult"));
        recommend.setEVE_STARTDATE(rcm.get("eventStartDate"));
        recommend.setEVE_STARTTIME(rcm.get("eventStartTime"));
        recommend.setEVE_STATUS(rcm.get("eventStatus"));
        recommend.setEVE_LEAGUETYPE(rcm.get("eventLeagueType"));
        recommend.setEVE_BALLTYPE(rcm.get("eventBallType"));
        recommend.setEVE_HOMETEAM(rcm.get("eventHomeTeam"));
        recommend.setEVE_VISITTEAM(rcm.get("eventVisitTeam"));
        recommend.setEVE_RESULT(rcm.get("eventResult"));
        recommendRepository.save(recommend);
        return ResponseMessage.ok();
    }

    @ApiOperation(value = "删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/remove/{id}")
    private Map<String, Object> removeRcm(@PathVariable("id") String rcm_id){

        return null;
    }

    @ApiOperation(value = "批量删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/batchremove")
    private Map<String, Object> batchRemoveRcm(){
        return null;
    }

    @ApiOperation(value = "更新修改", notes = "推单相关api")
    @PostMapping(path = "/rcm/edit")
    private Map<String, Object> editRcm(){
        return null;
    }

    @ApiOperation(value = "查询所有推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/list")
    private Map<String, Object> getRcmList(){
        return null;
    }

    @ApiOperation(value = "分页查询推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/listpage/{pagenum}")
    private Map<String, Object> getRcmListPage(){
        return null;
    }

    @ApiOperation(value = "增加", notes = "媒体机构相关api")
    @PutMapping(path = "/org/add")
    private Map<String, Object> addOrg(){
        return null;
    }

    @ApiOperation(value = "删除", notes = "媒体机构相关api")
    @DeleteMapping(path = "/org/remove")
    private Map<String, Object> removeOrg(){
        return null;
    }

    @ApiOperation(value = "批量删除", notes = "媒体机构相关api")
    @DeleteMapping(path = "/org/batchremove")
    private Map<String, Object> batchRemoveOrg(){
        return null;
    }

    @ApiOperation(value = "更新修改", notes = "媒体机构相关api")
    @PostMapping(path = "/org/edit")
    private Map<String, Object> editOrg(){
        return null;
    }

    @ApiOperation(value = "查询所有推单", notes = "媒体机构相关api")
    @GetMapping(path = "/org/list")
    private Map<String, Object> getOrgList(){
        return null;
    }

    @ApiOperation(value = "分页查询推单", notes = "媒体机构相关api")
    @GetMapping(path = "/org/listpage")
    private Map<String, Object> getOrgListPage(){
        return null;
    }

    @ApiOperation(value = "增加", notes = "公告相关api")
    @PutMapping(path = "/brd/add")
    private Map<String, Object> addBrd(){
        return null;
    }

    @ApiOperation(value = "删除", notes = "公告相关api")
    @DeleteMapping(path = "/brd/remove")
    private Map<String, Object> removeBrd(){
        return null;
    }

    @ApiOperation(value = "批量删除", notes = "公告相关api")
    @DeleteMapping(path = "/brd/batchremove")
    private Map<String, Object> batchRemoveBrd(){
        return null;
    }

    @ApiOperation(value = "更新修改", notes = "公告相关api")
    @PostMapping(path = "/brd/edit")
    private Map<String, Object> editBrd(){
        return null;
    }

    @ApiOperation(value = "查询所有推单", notes = "公告相关api")
    @GetMapping(path = "/brd/list")
    private Map<String, Object> getBrdList(){
        return null;
    }

    @ApiOperation(value = "分页查询推单", notes = "公告相关api")
    @GetMapping(path = "/brd/listpage")
    private Map<String, Object> getBrdListPage(){
        return null;
    }



}
