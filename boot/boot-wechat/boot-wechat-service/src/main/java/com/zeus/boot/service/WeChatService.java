package com.zeus.boot.service;

import com.zeus.boot.entity.Board;
import com.zeus.boot.vo.OrgDetails;
import com.zeus.boot.vo.OrgInfo;
import com.zeus.boot.vo.WinBoard;

import java.util.List;

public interface WeChatService {
    //根据组织类型，获取近（5，10，20）场胜率信息
    List<OrgInfo> getOrgInfosByOrgType(String orgType);

    //根据姓名模糊搜索名人或公众号
    List<OrgInfo> getOrgInfosByOrgName(String orgType, String orgName);

    //根据关键字模糊搜索名人或公众号
    List<OrgInfo> getOrgInfosByKeyword(String orgType, String keyword);

    //机构界面详情
    OrgDetails getOrgDetails(String orgId);

    //连红榜单查询
    List<WinBoard> getContinueWin();

    //公告查询
    List<Board> getBoards();
}
