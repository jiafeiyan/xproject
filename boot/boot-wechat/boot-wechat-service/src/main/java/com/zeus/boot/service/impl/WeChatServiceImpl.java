package com.zeus.boot.service.impl;

import com.zeus.boot.entity.Organization;
import com.zeus.boot.entity.Recommend;
import com.zeus.boot.repo.BoardRepository;
import com.zeus.boot.repo.OrganizationRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.WeChatService;
import com.zeus.boot.vo.OrgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class WeChatServiceImpl implements WeChatService{

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Transactional
    public List<OrgInfo> getOrgInfos(String orgType) {
        ArrayList<OrgInfo> orgInfoArrayList= new ArrayList<>();
        //查询所有机构信息
        List<Organization> orgs = organizationRepository.findAllByOrgType(orgType);
        //遍历查询每一个机构近20场数据
        for (int i = 0; i < orgs.size(); i++) {
            OrgInfo orgInfo = new OrgInfo();
            //获取机构ID
            Long orgId = orgs.get(i).getOrgId();
            //机构名称赋值
            String orgName = orgs.get(i).getOrgName();
            //根据ID查询近20场数据
            List<Recommend> rcms = recommendRepository.getTop20ByRcmRcmeridOrderByRcmDateDesc(String.valueOf(orgId));

            //计算近5场胜率
            Stream<Recommend> top5 = rcms.stream().limit(5);
            String rate5 = (top5.filter(recommend -> "2".equals(recommend.getRcmResult())).count() * 1.0) / (top5.count() * 1.0) + "%";

            //计算近10场胜率
            Stream<Recommend> top10 = rcms.stream().limit(10);
            String rate10 = (top10.filter(recommend -> "2".equals(recommend.getRcmResult())).count() * 1.0) / (top10.count() * 1.0) + "%";

            //计算近20场胜率
            Stream<Recommend> top20 = rcms.stream().limit(20);
            String rate20 = (top20.filter(recommend -> "2".equals(recommend.getRcmResult())).count() * 1.0) / (top20.count() * 1.0) + "%";

            orgInfo.setOrgId(orgId+"");
            orgInfo.setOrgName(orgName);
            orgInfo.setRate5(rate5);
            orgInfo.setRate10(rate10);
            orgInfo.setRate20(rate20);
            orgInfoArrayList.add(orgInfo);
        }
        return orgInfoArrayList;
    }
}
