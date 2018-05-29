package com.zeus.boot.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class OrgInfo {

    private String orgId;
    private String orgName;
    private String rate5;
    private String rate10;
    private String rate20;

}
