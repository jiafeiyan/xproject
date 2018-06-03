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
                @Index(name = "Organization_Index",columnList = "orgId")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orgId;

    @Column()
    @CreatedDate
    private String orgSetupdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织名字'")
    private String orgName;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织头像'")
    private String orgPic;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织二维码'")
    private String orgQrpic;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '身份简介'")
    private String orgIntroduction;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '宣传语'")
    private String orgMotto;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织类型'")
    private String orgType;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐指数'")
    private String orgRecommendindex;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '优先级别'")
    private String orgPriority;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '专家关键字'")
    private String orgKeyword;

    @Column(columnDefinition = "varchar(255) COMMENT '公众号归属人(机构)'")
    private String orgBelong;

    @Column(columnDefinition = "varchar(255) COMMENT '归属人联系方式'")
    private String orgContacts;

}

