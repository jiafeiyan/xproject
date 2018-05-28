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
                @Index(name = "Recommend_Index",columnList = "rcm_id")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Recommend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rcm_id;

    @Column()
    @CreatedDate
    private String rcm_setupdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐人ID'")
    private String rcm_rcmerid;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐人姓名'")
    private String rcm_rcmername;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐人类型'")
    private String rcm_rcmertype;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐简介'")
    private String rcm_introduction;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '付费标志'")
    private String rcm_payflag;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐日期'")
    private String rcm_date;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐时间'")
    private String rcm_time;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐内容'")
    private String rcm_content;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推单结果'")
    private String rcm_result;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛事开始日期'")
    private String eve_startdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛事开始时间'")
    private String eve_starttime;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛事状态'")
    private String eve_status;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '联赛种类'")
    private String eve_leaguetype;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '归属球种'")
    private String eve_balltype;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '主队'")
    private String eve_hometeam;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '客队'")
    private String eve_visitteam;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '赛果'")
    private String eve_result;
}
