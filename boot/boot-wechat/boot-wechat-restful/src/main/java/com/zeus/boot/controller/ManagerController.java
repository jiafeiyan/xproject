package com.zeus.boot.controller;

import com.zeus.boot.commons.message.ResponseMessage;
import com.zeus.boot.entity.Admin;
import com.zeus.boot.entity.Recommend;
import com.zeus.boot.repo.AdminRepository;
import com.zeus.boot.repo.BoardRepository;
import com.zeus.boot.repo.OrganizationRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.ManagerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 开发规范
  * 能直接访问Repository获取结果走Repository
  * 其他包含业务逻辑等事务处理都走Service
  * */
@Api(value = "Web Api", tags = {"web后台管理系统"})
@RestController
@RequestMapping(path = "/manager")
public class ManagerController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BoardRepository boardRepository;

    @ApiOperation(value = "管理员登陆接口")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常")})
    @PostMapping(path = "/login")
    private Map<String, Object> login(@RequestBody Map<String, String> user){
        String username = user.get("username");
        String password = user.get("password");
        Admin admin = adminRepository.findAdminByUsername(username);

        Map<String, Object> result = new HashMap<>();
        if(null == admin){
            result.put("error","user not find");
        }else{
            if(password.equals(admin.getPassword())){
                result.put("username", admin.getUsername());
                result.put("password", admin.getPassword());
                result.put("code", 200);
            }
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
    private ResponseMessage<Object> removeRcm(@PathVariable("id") Long rcm_id){
        Recommend recommend = new Recommend();
        recommend.setRCM_ID(rcm_id);
        recommendRepository.delete(recommend);
        return ResponseMessage.ok();
    }

    @ApiOperation(value = "批量删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/batchremove")
    private ResponseMessage<Object> batchRemoveRcm(@RequestParam List<Long> rcms){
        managerService.rcmBatchRemove(rcms);
        return ResponseMessage.ok();
    }

    @ApiOperation(value = "更新修改", notes = "推单相关api")
    @PostMapping(path = "/rcm/edit")
    private ResponseMessage<Object> editRcm(@RequestBody Map<String, String> rcm){
        Recommend recommend = new Recommend();
        recommend.setRCM_ID(Long.valueOf(rcm.get("rcmId")));
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

    @ApiOperation(value = "查询所有推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/list")
    private ResponseMessage<Object> getRcmList(){
        List<Recommend> all = recommendRepository.findAll();
        return ResponseMessage.ok(all);
    }

    @ApiOperation(value = "分页查询推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/listpage")
    private ResponseMessage<Page<Recommend>> getRcmListPage(@PageableDefault() Pageable pageable){
        Page<Recommend> page = recommendRepository.findAll(pageable);
        return ResponseMessage.ok(page);
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
