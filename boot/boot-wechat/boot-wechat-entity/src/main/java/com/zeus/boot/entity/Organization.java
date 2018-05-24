package com.zeus.boot.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(
        name = "t_Organization",
        indexes = {
                @Index(name = "Organization_Index",columnList = "ORG_ID")
        }
)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "varchar(255) COMMENT '组织ID'")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ORG_ID;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '创建日期'")
    private String ORG_SETUPDATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '创建时间'")
    private String ORG_SETUPTIME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '组织名字'")
    private String ORG_NAME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '组织头像'")
    private String ORG_PIC;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '组织二维码'")
    private String ORG_QRPIC;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '身份简介'")
    private String ORG_INTRODUCTION;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '宣传语'")
    private String ORG_MOTTO;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '组织类型'")
    private String ORG_TYPE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐指数'")
    private String ORG_RECOMMENDINDEX;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '优先级别'")
    private String ORG_PRIORITY;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '专家关键字'")
    private String ORG_KEYWORD;

    @Column(columnDefinition = "varchar(255) COMMENT '公众号归属人(机构)'")
    private String ORG_BELONG;

    @Column(columnDefinition = "varchar(255) COMMENT '归属人联系方式'")
    private String ORG_CONTACTS;

}

