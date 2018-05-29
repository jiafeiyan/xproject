package com.zeus.boot.service.impl;

import com.zeus.boot.entity.Organization;
import com.zeus.boot.entity.Recommend;
import com.zeus.boot.repo.OrganizationRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.WeChatService;
import com.zeus.boot.vo.OrgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Map<Object, Object> getOrgInfos() {
        HashMap<Object, Object> orgInfosMap = new HashMap<>();
        //查询所有机构信息
        List<Organization> orgs = organizationRepository.findAll();
        //遍历查询每一个机构近20场数据
        for (int i = 0; i < orgs.size(); i++) {
            OrgInfo orgInfo = new OrgInfo();
            //获取ID
            Long orgId = orgs.get(i).getOrg_id();
            String orgName = orgs.get(i).getOrg_name();
            //根据ID查询近20场数据
            List<Recommend> rcms = recommendRepository.findAll();
            //将推单结果字段组合成数组
            ArrayList rcmResultList = new ArrayList();
            for (int j = 0; i < rcms.size(); j++) {
                String result = rcms.get(j).getRcm_result();
                if ("2" == result) {
                    rcmResultList.add(true);
                } else {
                    rcmResultList.add(false);
                }

            }
            //计算近5场胜率
            String rate5 = (rcmResultList.stream().limit(5).filter(k -> true).count() * 1.0) / (rcmResultList.stream().limit(5).count() * 1.0) + "%";
            //计算近10场胜率
            String rate10 = (rcmResultList.stream().limit(10).filter(k -> true).count() * 1.0) / (rcmResultList.stream().limit(10).count() * 1.0) + "%";
            //计算近20场胜率
            String rate20 = (rcmResultList.stream().limit(20).filter(k -> true).count() * 1.0) / (rcmResultList.stream().limit(20).count() * 1.0) + "%";
            orgInfo.setOrgId(orgId+"");


        }
        return orgInfosMap;
    }
}
