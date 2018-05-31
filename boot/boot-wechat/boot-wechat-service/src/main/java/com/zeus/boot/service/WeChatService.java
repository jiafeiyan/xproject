package com.zeus.boot.service;

import com.zeus.boot.vo.OrgInfo;

import java.util.List;

public interface WeChatService {
    List<OrgInfo> getOrgInfos(String orgType);
}
