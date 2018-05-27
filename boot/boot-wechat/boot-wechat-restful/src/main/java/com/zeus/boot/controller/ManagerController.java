package com.zeus.boot.controller;

import com.zeus.boot.commons.message.ResponseMessage;
import com.zeus.boot.commons.message.ResultCode;
import com.zeus.boot.entity.Admin;
import com.zeus.boot.entity.Recommend;
import com.zeus.boot.repo.AdminRepository;
import com.zeus.boot.repo.BoardRepository;
import com.zeus.boot.repo.OrganizationRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.ManagerService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    private ResponseMessage addRcm(@RequestBody Map<String, Map<String, String>> rcm){
        try {
            Map<String, String> params = rcm.get("params");
            Recommend recommend = new Recommend();
            recommend.setRcm_rcmerid(params.get("rcmerId"));
            recommend.setRcm_rcmername(params.get("rcmerName"));
            recommend.setRcm_rcmertype(params.get("rcmerType"));
            recommend.setRcm_introduction(params.get("rcmIntrocution"));
            recommend.setRcm_payflag(params.get("rcmPayFlag"));
            recommend.setRcm_date(params.get("rcmDate"));
            recommend.setRcm_time(params.get("rcmTime"));
            recommend.setRcm_content(params.get("rcmContent"));
            recommend.setRcm_result(params.get("rcmResult"));
            recommend.setEve_startdate(params.get("eventStartDate"));
            recommend.setEve_starttime(params.get("eventStartTime"));
            recommend.setEve_status(params.get("eventStatus"));
            recommend.setEve_leaguetype(params.get("eventLeagueType"));
            recommend.setEve_balltype(params.get("eventBallType"));
            recommend.setEve_hometeam(params.get("eventHomeTeam"));
            recommend.setEve_visitteam(params.get("eventVisitTeam"));
            recommend.setEve_result(params.get("eventResult"));
            recommendRepository.save(recommend);
            return ResponseMessage.ok();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/add ] 内部错误");
        }
    }

    @ApiOperation(value = "删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/remove/{id}")
    private ResponseMessage<Object> removeRcm(@PathVariable("id") Long rcm_id){
        try {
            Recommend recommend = new Recommend();
            recommend.setRcm_id(rcm_id);
            recommendRepository.delete(recommend);
            return ResponseMessage.ok();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/remove ] 内部错误");
        }
    }

    @ApiOperation(value = "批量删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/batchremove")
    private ResponseMessage<Object> batchRemoveRcm(@RequestParam Map<String, String> rcms){
        try {
            String ids = (String) rcms.get("ids");
            String[] arr = ids.split(",");
            List<String> list = java.util.Arrays.asList(arr);
            managerService.rcmBatchRemove(list);
            return ResponseMessage.ok();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/batchremove ] 内部错误");
        }
    }

    @ApiOperation(value = "更新修改", notes = "推单相关api")
    @PostMapping(path = "/rcm/edit")
    private ResponseMessage<Object> editRcm(@RequestBody Map<String, Map<String, String>> rcm){
        try {
            Map<String, String> params = rcm.get("params");
            Recommend recommend = new Recommend();
            recommend.setRcm_id(Long.valueOf(params.get("rcm_id")));
            recommend.setRcm_rcmerid(params.get("rcm_rcmerid"));
            recommend.setRcm_rcmername(params.get("rcm_rcmername"));
            recommend.setRcm_rcmertype(params.get("rcm_rcmertype"));
            recommend.setRcm_introduction(params.get("rcm_introduction"));
            recommend.setRcm_payflag(params.get("rcm_payflag"));
            recommend.setRcm_date(params.get("rcm_date"));
            recommend.setRcm_time(params.get("rcm_time"));
            recommend.setRcm_content(params.get("rcm_content"));
            recommend.setRcm_result(params.get("rcm_result"));
            recommend.setEve_startdate(params.get("eve_startdate"));
            recommend.setEve_starttime(params.get("eve_starttime"));
            recommend.setEve_status(params.get("eve_status"));
            recommend.setEve_leaguetype(params.get("eve_leaguetype"));
            recommend.setEve_balltype(params.get("eve_balltype"));
            recommend.setEve_hometeam(params.get("eve_hometeam"));
            recommend.setEve_visitteam(params.get("eve_visitteam"));
            recommend.setEve_result(params.get("eve_result"));
            recommendRepository.save(recommend);
            return ResponseMessage.ok();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/edit ] 内部错误");
        }
    }

    @ApiOperation(value = "查询所有推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/list")
    private ResponseMessage<Object> getRcmList(){
        try {
            List<Recommend> all = recommendRepository.findAll();
            return ResponseMessage.ok(all);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/list ] 内部错误");
        }
    }

    @ApiOperation(value = "分页查询推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/listpage")
    private ResponseMessage<Page<Recommend>> getRcmListPage(@PageableDefault() Pageable pageable){
        try {
            Page<Recommend> page = recommendRepository.findAll(pageable);
            return ResponseMessage.ok(page);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/listpage ] 内部错误");
        }
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
