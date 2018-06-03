package com.zeus.boot.service.impl;

import com.zeus.boot.entity.Board;
import com.zeus.boot.entity.Organization;
import com.zeus.boot.entity.Recommend;
import com.zeus.boot.repo.BoardRepository;
import com.zeus.boot.repo.OrganizationRepository;
import com.zeus.boot.repo.RecommendRepository;
import com.zeus.boot.service.WeChatService;
import com.zeus.boot.vo.OrgDetails;
import com.zeus.boot.vo.OrgInfo;
import com.zeus.boot.vo.WinBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BoardRepository boardRepository;

    /**
     * 查询机构近（5，10，20）胜率情况
     *
     * @param orgType
     * @return
     */
    @Override
    @Transactional
    public List<OrgInfo> getOrgInfosByOrgType(String orgType) {
        ArrayList<OrgInfo> orgInfoArrayList = new ArrayList<>();
        //查询所有机构信息
        List<Organization> orgs = organizationRepository.findAllByOrgType(orgType);
        //遍历查询每一个机构近20场数据
        getWinningRate(orgInfoArrayList, orgs);
        return orgInfoArrayList;
    }

    /**
     * 根据组织类型，组织名字模糊查询组织信息
     *
     * @param orgType
     * @param orgName
     * @return
     */
    @Override
    public List<OrgInfo> getOrgInfosByOrgName(String orgType, String orgName) {
        ArrayList<OrgInfo> orgInfoArrayList = new ArrayList<>();
        //根据机构类型，机构名称查询机构信息
        List<Organization> orgs = organizationRepository.findAllByOrgTypeAndOrgName(orgType, orgName);
        //遍历查询每一个机构近20场数据
        getWinningRate(orgInfoArrayList, orgs);
        return orgInfoArrayList;
    }

    /**
     * 根据组织类型，关键字查询组织信息
     *
     * @param orgType
     * @param keyword
     * @return
     */
    @Override
    public List<OrgInfo> getOrgInfosByKeyword(String orgType, String keyword) {
        ArrayList<OrgInfo> orgInfoArrayList = new ArrayList<>();
        //查询所有机构信息
        List<Organization> orgs = organizationRepository.findAllByOrgTypeAndOrgKeyword(orgType, keyword);
        //遍历查询每一个机构近20场数据
        getWinningRate(orgInfoArrayList, orgs);
        return orgInfoArrayList;
    }

    /**
     * 查询机构详情界面，机构信息，近20场推单情况
     *
     * @param orgId
     * @return
     */
    @Override
    public OrgDetails getOrgDetails(String orgId) {
        OrgDetails orgDetails = new OrgDetails();
        //根据机构ID查询机构信息
        Organization organization = organizationRepository.getOne(Long.parseLong(orgId));
        orgDetails.setOrgId(String.valueOf(organization.getOrgId()));
        orgDetails.setOrgBelong(organization.getOrgBelong());
        orgDetails.setOrgIntroduction(organization.getOrgIntroduction());
        orgDetails.setOrgMotto(organization.getOrgMotto());
        orgDetails.setOrgName(organization.getOrgName());
        orgDetails.setOrgPic(organization.getOrgPic());
        orgDetails.setOrgQRPic(organization.getOrgPic());
        Pageable pageable = PageRequest.of(0,20);
        Page<Recommend> pageRecommendList = recommendRepository.findByRcmRcmerid(orgId,pageable);
        List<Recommend> recommendList = pageRecommendList.getContent();
        orgDetails.setList(recommendList);
        return orgDetails;
    }

    /**
     * 连红榜查询
     *
     * @return
     */
    @Override
    public List<WinBoard> getContinueWin() {
        ArrayList<WinBoard> winBoards = new ArrayList<>();
        //查询所有机构
        List<Organization> organizations = organizationRepository.findAll();
        //遍历所有机构
        for (Organization organization : organizations) {
            WinBoard winBoard = new WinBoard();
            //获取机构ID
            Long orgId = organization.getOrgId();
            String orgName = organization.getOrgName();
            //查询机构所有推单
            final List<Recommend> recommends = recommendRepository.findByRcmRcmerid(String.valueOf(orgId));
            //获取所有推单结果，放入数组
            ArrayList arrayList = new ArrayList();
            for (Recommend recommend : recommends) {
                arrayList.add(recommend.getRcmResult());
            }
            //判断连红场数
            int k = 0;
            for (Object anArrayList : arrayList) {
                if (anArrayList.equals("2")) {
                    k++;
                } else {
                    break;
                }
            }
            winBoard.setOrgId(String.valueOf(orgId));
            winBoard.setOrgName(orgName);
            winBoard.setContinueWin(String.valueOf(k));
            winBoards.add(winBoard);

        }
        return winBoards;
    }

    /**
     * 公告查询
     *
     * @return
     */
    @Override
    public List<Board> getBoards() {
        ArrayList<Board> boards = new ArrayList<>();
        List<Board> all = boardRepository.findAll();
        return all;
    }

    private void getWinningRate(ArrayList<OrgInfo> orgInfoArrayList, List<Organization> orgs) {
        for (int i = 0; i < orgs.size(); i++) {
            OrgInfo orgInfo = new OrgInfo();
            //获取机构ID
            Long orgId = orgs.get(i).getOrgId();
            //机构名称赋值
            String orgName = orgs.get(i).getOrgName();
            //根据ID查询近20场数据
            Pageable pageable = PageRequest.of(0,20);
            Page<Recommend> pageRcms = recommendRepository.findByRcmRcmerid(String.valueOf(orgId),pageable);
            List<Recommend> rcms = pageRcms.getContent();
            String rate5;
            String rate10;
            String rate20;
            //计算近5场胜率
            if(rcms.size() >= 5){
                Stream<Recommend> top5 = rcms.stream().limit(5);
                rate5 = (top5.filter(recommend -> "2".equals(recommend.getRcmResult())).count() * 1.0) / (top5.count() * 1.0) + "%";
            }else{
                rate5 = "--";
            }
            //计算近10场胜率
            if(rcms.size() >= 10){
                Stream<Recommend> top10 = rcms.stream().limit(10);
                rate10 = (top10.filter(recommend -> "2".equals(recommend.getRcmResult())).count() * 1.0) / (top10.count() * 1.0) + "%";
            }else{
                rate10 = "--";
            }
            //计算近20场胜率
            if(rcms.size() >= 20){
                Stream<Recommend> top20 = rcms.stream().limit(20);
                rate20 = (top20.filter(recommend -> "2".equals(recommend.getRcmResult())).count() * 1.0) / (top20.count() * 1.0) + "%";
            }else{
                rate20 = "--";
            }
            orgInfo.setOrgId(orgId + "");
            orgInfo.setOrgName(orgName);
            orgInfo.setRate5(rate5);
            orgInfo.setRate10(rate10);
            orgInfo.setRate20(rate20);
            orgInfoArrayList.add(orgInfo);
        }
    }
}
