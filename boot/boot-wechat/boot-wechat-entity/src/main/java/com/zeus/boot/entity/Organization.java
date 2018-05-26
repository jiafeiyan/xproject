package com.zeus.boot.entity;


import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ORG_ID;

    @Column()
    @CreatedDate
    private String ORG_SETUPDATE;

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

