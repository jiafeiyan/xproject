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
        name = "tRecommend",
        indexes = {
                @Index(name = "Recommend_Index",columnList = "rcmId")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Recommend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rcmId;

    @Column()
    @CreatedDate
    private String rcmSetupdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐人ID'")
    private String rcmRcmerid;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐人姓名'")
    private String rcmRcmername;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐人类型'")
    private String rcmRcmertype;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐简介'")
    private String rcmIntroduction;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '付费标志'")
    private String rcmPayflag;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐日期'")
    private String rcmDate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐时间'")
    private String rcmTime;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐内容'")
    private String rcmContent;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推单结果'")
    private String rcmResult;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛事开始日期'")
    private String eveStartdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛事开始时间'")
    private String eveStarttime;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛事状态'")
    private String eveStatus;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '联赛种类'")
    private String eveLeaguetype;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '归属球种'")
    private String eveBalltype;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '主队'")
    private String eveHometeam;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '客队'")
    private String eveVisitteam;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛果'")
    private String eveResult;
}
