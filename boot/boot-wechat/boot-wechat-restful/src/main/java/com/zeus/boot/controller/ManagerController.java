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
            recommend.setRcmRcmerid(params.get("rcmerId"));
            Organization org = organizationRepository.getOne(Long.parseLong(params.get("rcmerId")));
            recommend.setRcmRcmername(org.getOrgName());
            recommend.setRcmRcmertype(org.getOrgType());
            recommend.setRcmIntroduction(params.get("rcmIntroduction"));
            recommend.setRcmPayflag(params.get("rcmPayFlag"));
            recommend.setRcmDate(params.get("rcmDate"));
            recommend.setRcmTime(params.get("rcmTime"));
            recommend.setRcmContent(params.get("rcmContent"));
            recommend.setRcmResult(params.get("rcmResult"));
            recommend.setEveStartdate(params.get("eventStartDate"));
            recommend.setEveStarttime((params.get("eventStartTime")).length() > 19 ? (params.get("eventStartTime")).substring(11, 19) : "");
            recommend.setEveStatus(params.get("eventStatus"));
            recommend.setEveLeaguetype(params.get("eventLeagueType"));
            recommend.setEveBalltype(params.get("eventBallType"));
            recommend.setEveHometeam(params.get("eventHomeTeam"));
            recommend.setEveVisitteam(params.get("eventVisitTeam"));
            recommend.setEveResult(params.get("eventResult"));
            recommendRepository.save(recommend);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /rcm/add ] 内部错误");
        }
    }

    @ApiOperation(value = "删除", notes = "推单相关api")
    @DeleteMapping(path = "/rcm/remove/{id}")
    private ResponseMessage<Object> removeRcm(@PathVariable("id") Long rcmId) {
        try {
            Recommend recommend = new Recommend();
            recommend.setRcmId(rcmId);
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
            recommend.setRcmSetupdate(params.get("rcmSetupdate"));
            recommend.setRcmId(Long.valueOf(params.get("rcmId")));
            recommend.setRcmRcmerid(params.get("rcmRcmerid"));
            Organization org = organizationRepository.getOne(Long.parseLong(params.get("rcmRcmerid")));
            recommend.setRcmRcmername(org.getOrgName());
            recommend.setRcmRcmertype(org.getOrgType());
            recommend.setRcmIntroduction(params.get("rcmIntroduction"));
            recommend.setRcmPayflag(params.get("rcmPayflag"));
            recommend.setRcmDate(params.get("rcmDate"));
            recommend.setRcmTime(params.get("rcmTime"));
            recommend.setRcmContent(params.get("rcmContent"));
            recommend.setRcmResult(params.get("rcmResult"));
            recommend.setEveStartdate(params.get("eveStartdate"));
            recommend.setEveStarttime((params.get("eventStartTime")).length() > 19 ? (params.get("eventStartTime")).substring(11, 19) : "");
            recommend.setEveStatus(params.get("eveStatus"));
            recommend.setEveLeaguetype(params.get("eveLeaguetype"));
            recommend.setEveBalltype(params.get("eveBalltype"));
            recommend.setEveHometeam(params.get("eveHometeam"));
            recommend.setEveVisitteam(params.get("eveVisitteam"));
            recommend.setEveResult(params.get("eveResult"));
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
            organization.setOrgType(params.get("orgType"));
            organization.setOrgRecommendindex(params.get("orgRecommendIndex"));
            organization.setOrgPriority(params.get("orgPriority"));
            organization.setOrgName(params.get("orgName"));
            organization.setOrgMotto(params.get("orgMotto"));
            organization.setOrgKeyword(params.get("orgKeyword"));
            organization.setOrgIntroduction(params.get("orgIntroduction"));
            organization.setOrgContacts(params.get("orgContacts"));
            organization.setOrgBelong(params.get("orgBelong"));
            organizationRepository.save(organization);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /org/add ] 内部错误");
        }
    }


    @ApiOperation(value = "删除", notes = "媒体机构相关api")
    @DeleteMapping(path = "/org/remove/{id}")
    private ResponseMessage<Object> removeOrg(@PathVariable("id") Long orgId) {
        try {
            Organization organization = new Organization();
            organization.setOrgId(orgId);
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
            organization.setOrgSetupdate(params.get("orgSetupdate"));
            organization.setOrgId(Long.valueOf(params.get("orgId")));
            organization.setOrgType(params.get("orgType"));
            organization.setOrgRecommendindex(params.get("orgRecommendindex"));
            organization.setOrgPriority(params.get("orgPriority"));
            organization.setOrgName(params.get("orgName"));
            organization.setOrgMotto(params.get("orgMotto"));
            organization.setOrgKeyword(params.get("orgKeyword"));
            organization.setOrgIntroduction(params.get("orgIntroduction"));
            organization.setOrgContacts(params.get("orgContacts"));
            organization.setOrgBelong(params.get("orgBelong"));
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
            board.setBrdRcmdate(params.get("brdRcmdate"));
            board.setBrdRcmtime((params.get("brdRcmtime")).length() > 19 ? (params.get("brdRcmtime")).substring(11, 19) : "");
            board.setBrdRcmcontent(params.get("brdRcmcontent"));
            board.setBrdRcmreason(params.get("brdRcmreason"));
            boardRepository.save(board);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessage.error(ResultCode.INTERNAL_SERVER_ERROR, "接口 [ /brd/add ] 内部错误");
        }
    }

    @ApiOperation(value = "删除", notes = "公告相关api")
    @DeleteMapping(path = "/brd/remove/{id}")
    private ResponseMessage<Object> removeBrd(@PathVariable("id") Long brdId) {
        try {
            Board board = new Board();
            board.setBrdId(brdId);
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
            board.setBrdSetupdate(params.get("brdSetupdate"));
            board.setBrdId(Long.valueOf(params.get("brdId")));
            board.setBrdRcmdate(params.get("brdRcmdate"));
            board.setBrdRcmtime((params.get("brdRcmtime")).length() > 19 ? (params.get("brdRcmtime")).substring(11, 19) : "");
            board.setBrdRcmcontent(params.get("brdRcmcontent"));
            board.setBrdRcmreason(params.get("brdRcmreason"));
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
