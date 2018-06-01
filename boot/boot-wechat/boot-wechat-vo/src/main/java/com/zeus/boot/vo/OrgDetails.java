package com.zeus.boot.vo;

import com.zeus.boot.entity.Recommend;
import lombok.Data;

import java.util.List;

@Data
public class OrgDetails {
    private String orgId;
    private String orgPic;
    private String orgName;
    private String orgIntroduction;
    private String orgBelong;
    private String orgMotto;
    private String orgQRPic;
    private List<Recommend> list;
}
