package com.zeus.boot.controller;

import com.zeus.boot.commons.message.ResponseMessage;
import com.zeus.boot.commons.message.ResultCode;
import com.zeus.boot.entity.Board;
import com.zeus.boot.entity.Organization;
import com.zeus.boot.entity.Recommend;
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
    private RecommendRepository recommendRepository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BoardRepository boardRepository;

    @ApiOperation(value = "增加", notes = "推单相关api")
    @PutMapping(path = "/rcm/add")
    private ResponseMessage addRcm(@RequestBody Map<String, Map<String, String>> rcm) {
        try {
            Map<String, String> params = rcm.get("params");
            Recommend recommend = new Recommend();
            recommend.setRcm_rcmerid(params.get("rcmerId"));
            Organization org = organizationRepository.getOne(Long.parseLong(params.get("rcmerId")));
            recommend.setRcm_rcmername(org.getOrg_name());
            recommend.setRcm_rcmertype(org.getOrg_type());
            recommend.setRcm_introduction(params.get("rcmIntrocution"));
            recommend.setRcm_payflag(params.get("rcmPayFlag"));
            recommend.setRcm_date(params.get("rcmDate"));
            recommend.setRcm_time(params.get("rcmTime"));
            recommend.setRcm_content(params.get("rcmContent"));
            recommend.setRcm_result(params.get("rcmResult"));
            recommend.setEve_startdate(params.get("eventStartDate"));
            recommend.setEve_starttime((params.get("eventStartTime")).length() > 19 ? (params.get("eventStartTime")).substring(11, 19) : "");
            recommend.setEve_status(params.get("eventStatus"));
            recommend.setEve_leaguetype(params.get("eventLeagueType"));
            recommend.setEve_balltype(params.get("eventBallType"));
            recommend.setEve_hometeam(params.get("eventHomeTeam"));
            recommend.setEve_visitteam(params.get("eventVisitTeam"));
            recommend.setEve_result(params.get("eventResult"));
            recommendRepository.save(recommend);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/add ] 内部错误");
        }
    }

    @ApiOperation(value = "删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/remove/{id}")
    private ResponseMessage<Object> removeRcm(@PathVariable("id") Long rcm_id) {
        try {
            Recommend recommend = new Recommend();
            recommend.setRcm_id(rcm_id);
            recommendRepository.delete(recommend);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/remove ] 内部错误");
        }
    }

    @ApiOperation(value = "批量删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/batchremove")
    private ResponseMessage<Object> batchRemoveRcm(@RequestParam Map<String, String> rcms) {
        try {
            String ids = (String) rcms.get("ids");
            String[] arr = ids.split(",");
            List<String> list = java.util.Arrays.asList(arr);
            managerService.rcmBatchRemove(list);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/batchremove ] 内部错误");
        }
    }

    @ApiOperation(value = "更新修改", notes = "推单相关api")
    @PostMapping(path = "/rcm/edit")
    private ResponseMessage<Object> editRcm(@RequestBody Map<String, Map<String, String>> rcm) {
        try {
            Map<String, String> params = rcm.get("params");
            Recommend recommend = new Recommend();
            recommend.setRcm_setupdate(params.get("rcm_setupdate"));
            recommend.setRcm_id(Long.valueOf(params.get("rcm_id")));
            recommend.setRcm_rcmerid(params.get("rcm_rcmerid"));
            Organization org = organizationRepository.getOne(Long.parseLong(params.get("rcm_rcmerid")));
            recommend.setRcm_rcmername(org.getOrg_name());
            recommend.setRcm_rcmertype(org.getOrg_type());
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/edit ] 内部错误");
        }
    }

    @ApiOperation(value = "查询所有推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/list")
    private ResponseMessage<Object> getRcmList() {
        try {
            List<Recommend> all = recommendRepository.findAll();
            return ResponseMessage.ok(all);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/list ] 内部错误");
        }
    }

    @ApiOperation(value = "分页查询推单", notes = "推单相关api")
    @GetMapping(path = "/rcm/listpage")
    private ResponseMessage<Page<Recommend>> getRcmListPage(@PageableDefault() Pageable pageable) {
        try {
            Page<Recommend> page = recommendRepository.findAll(pageable);
            return ResponseMessage.ok(page);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/listpage ] 内部错误");
        }
    }

    @ApiOperation(value = "增加", notes = "媒体机构相关api")
    @PutMapping(path = "/org/add")
    private ResponseMessage addOrg(@RequestBody Map<String, Map<String, String>> org) {
        try {
            Map<String, String> params = org.get("params");
            Organization organization = new Organization();
            organization.setOrg_type(params.get("orgType"));
            organization.setOrg_recommendindex(params.get("orgRecommendIndex"));
            organization.setOrg_priority(params.get("orgPriority"));
            organization.setOrg_name(params.get("orgName"));
            organization.setOrg_motto(params.get("orgMotto"));
            organization.setOrg_keyword(params.get("orgKeyword"));
            organization.setOrg_introduction(params.get("orgIntroduction"));
            organization.setOrg_contacts(params.get("orgContacts"));
            organization.setOrg_belong(params.get("orgBelong"));
            organizationRepository.save(organization);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /org/add ] 内部错误");
        }
    }


    @ApiOperation(value = "删除", notes = "媒体机构相关api")
    @DeleteMapping(path = "/org/remove/{id}")
    private ResponseMessage<Object> removeOrg(@PathVariable("id") Long org_id) {
        try {
            Organization organization = new Organization();
            organization.setOrg_id(org_id);
            organizationRepository.delete(organization);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /org/remove ] 内部错误");
        }
    }

    @ApiOperation(value = "批量删除", notes = "媒体机构相关api")
    @DeleteMapping(path = "/org/batchremove")
    private ResponseMessage<Object> batchRemoveOrg(@RequestParam Map<String, String> orgs) {
        try {
            String ids = (String) orgs.get("ids");
            String[] arr = ids.split(",");
            List<String> list = java.util.Arrays.asList(arr);
            managerService.orgBatchRemove(list);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /org/batchremove ] 内部错误");
        }
    }

    @ApiOperation(value = "更新修改", notes = "媒体机构相关api")
    @PostMapping(path = "/org/edit")
    private ResponseMessage<Object> editOrg(@RequestBody Map<String, Map<String, String>> org) {
        try {
            Map<String, String> params = org.get("params");
            Organization organization = new Organization();
            organization.setOrg_setupdate(params.get("org_setupdate"));
            organization.setOrg_id(Long.valueOf(params.get("org_id")));
            organization.setOrg_type(params.get("org_type"));
            organization.setOrg_recommendindex(params.get("org_recommendindex"));
            organization.setOrg_priority(params.get("org_priority"));
            organization.setOrg_name(params.get("org_name"));
            organization.setOrg_motto(params.get("org_motto"));
            organization.setOrg_keyword(params.get("org_keyword"));
            organization.setOrg_introduction(params.get("org_introduction"));
            organization.setOrg_contacts(params.get("org_contacts"));
            organization.setOrg_belong(params.get("org_belong"));
            organizationRepository.save(organization);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /org/edit ] 内部错误");
        }
    }

    @ApiOperation(value = "查询所有机构", notes = "媒体机构相关api")
    @GetMapping(path = "/org/list")
    private ResponseMessage<Object> getOrgList() {
        try {
            List<Organization> all = organizationRepository.findAll();
            return ResponseMessage.ok(all);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /org/list ] 内部错误");
        }
    }

    @ApiOperation(value = "分页查询机构", notes = "媒体机构相关api")
    @GetMapping(path = "/org/listpage")
    private ResponseMessage<Page<Organization>> getOrgListPage(@PageableDefault() Pageable pageable) {
        try {
            Page<Organization> page = organizationRepository.findAll(pageable);
            return ResponseMessage.ok(page);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /org/listpage ] 内部错误");
        }
    }

    @ApiOperation(value = "增加", notes = "公告相关api")
    @PutMapping(path = "/brd/add")
    private ResponseMessage addBrd(@RequestBody Map<String, Map<String, String>> brd) {
        try {
            Map<String, String> params = brd.get("params");
            Board board = new Board();
            board.setBrd_rcmdate(params.get("brdRcmdate"));
            board.setBrd_rcmtime((params.get("brdRcmtime")).length() > 19 ? (params.get("brdRcmtime")).substring(11, 19) : "");
            board.setBrd_rcmcontent(params.get("brdRcmcontent"));
            board.setBrd_rcmreason(params.get("brdRcmreason"));
            boardRepository.save(board);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /brd/add ] 内部错误");
        }
    }

    @ApiOperation(value = "删除", notes = "公告相关api")
    @DeleteMapping(path = "/brd/remove/{id}")
    private ResponseMessage<Object> removeBrd(@PathVariable("id") Long brd_id) {
        try {
            Board board = new Board();
            board.setBrd_id(brd_id);
            boardRepository.delete(board);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /brd/remove ] 内部错误");
        }
    }

    @ApiOperation(value = "批量删除", notes = "公告相关api")
    @DeleteMapping(path = "/brd/batchremove")
    private ResponseMessage<Object> batchRemoveBrd(@RequestParam Map<String, String> brds) {
        try {
            String ids = (String) brds.get("ids");
            String[] arr = ids.split(",");
            List<String> list = java.util.Arrays.asList(arr);
            managerService.brdBatchRemove(list);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /brd/batchremove ] 内部错误");
        }
    }

    @ApiOperation(value = "更新修改", notes = "公告相关api")
    @PostMapping(path = "/brd/edit")
    private ResponseMessage<Object> editBrd(@RequestBody Map<String, Map<String, String>> brd) {
        try {
            Map<String, String> params = brd.get("params");
            Board board = new Board();
            board.setBrd_setupdate(params.get("brd_setupdate"));
            board.setBrd_id(Long.valueOf(params.get("brd_id")));
            board.setBrd_rcmdate(params.get("brd_rcmdate"));
            board.setBrd_rcmtime((params.get("brd_rcmtime")).length() > 19 ? (params.get("brd_rcmtime")).substring(11, 19) : "");
            board.setBrd_rcmcontent(params.get("brd_rcmcontent"));
            board.setBrd_rcmreason(params.get("brd_rcmreason"));
            boardRepository.save(board);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /brd/edit ] 内部错误");
        }
    }

    @ApiOperation(value = "查询所有公告", notes = "公告相关api")
    @GetMapping(path = "/brd/list")
    private ResponseMessage<Object> getBrdList() {
        try {
            List<Board> all = boardRepository.findAll();
            return ResponseMessage.ok(all);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /brd/list ] 内部错误");
        }
    }

    @ApiOperation(value = "分页查询公告", notes = "公告相关api")
    @GetMapping(path = "/brd/listpage")
    private ResponseMessage<Page<Board>> getBrdListPage(@PageableDefault() Pageable pageable) {
        try {
            Page<Board> page = boardRepository.findAll(pageable);
            return ResponseMessage.ok(page);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /brd/listpage ] 内部错误");
        }
    }


}
