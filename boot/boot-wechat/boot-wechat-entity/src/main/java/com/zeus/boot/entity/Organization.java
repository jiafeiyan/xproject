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
                @Index(name = "Organization_Index",columnList = "org_id")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long org_id;

    @Column()
    @CreatedDate
    private String org_setupdate;

    @Column()
    @CreatedDate
    private String org_setuptime;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织名字'")
    private String org_name;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织头像'")
    private String org_pic;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织二维码'")
    private String org_qrpic;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '身份简介'")
    private String org_introduction;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '宣传语'")
    private String org_motto;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '组织类型'")
    private String org_type;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐指数'")
    private String org_recommendindex;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '优先级别'")
    private String org_priority;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '专家关键字'")
    private String org_keyword;

    @Column(columnDefinition = "varchar(255) COMMENT '公众号归属人(机构)'")
    private String org_belong;

    @Column(columnDefinition = "varchar(255) COMMENT '归属人联系方式'")
    private String org_contacts;

}

