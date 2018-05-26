package com.zeus.boot.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(
        name = "t_Recommend",
        indexes = {
                @Index(name = "Recommend_Index",columnList = "RCM_ID")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Recommend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RCM_ID;

    @Column()
    @CreatedDate
    private String RCM_SETUPDATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐人ID'")
    private String RCM_RCMERID;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐人姓名'")
    private String RCM_RCMERNAME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐人类型'")
    private String RCM_RCMERTYPE;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐简介'")
    private String RCM_INTRODUCTION;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '付费标志'")
    private String RCM_PAYFLAG;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐日期'")
    private String RCM_DATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐时间'")
    private String RCM_TIME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐内容'")
    private String RCM_CONTENT;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推单结果'")
    private String RCM_RESULT;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '赛事开始日期'")
    private String EVE_STARTDATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '赛事开始时间'")
    private String EVE_STARTTIME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '赛事状态'")
    private String EVE_STATUS;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '联赛种类'")
    private String EVE_LEAGUETYPE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '归属球种'")
    private String EVE_BALLTYPE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '主队'")
    private String EVE_HOMETEAM;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '客队'")
    private String EVE_VISITTEAM;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '赛果'")
    private String EVE_RESULT;
}