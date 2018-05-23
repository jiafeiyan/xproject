package com.zeus.boot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(
        name = "t_Recommend",
        indexes = {
                @Index(name = "Recommend_Index",columnList = "RCM_ID")
        }
)
public class Recommend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "推荐ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RCM_ID;

    @Column(nullable = false, columnDefinition = "创建日期")
    private String RCM_SETUPDATE;

    @Column(nullable = false, columnDefinition = "创建时间")
    private String RCM_SETUPTIME;

    @Column(nullable = false, columnDefinition = "推荐人ID")
    private String RCM_RCMERID;

    @Column(nullable = false, columnDefinition = "推荐人姓名")
    private String RCM_RCMERNAME;

    @Column(nullable = false, columnDefinition = "推荐人类型")
    private String RCM_RCMERTYPE;

    @Column(nullable = true, columnDefinition = "推荐简介")
    private String RCM_INTRODUCTION;

    @Column(nullable = false, columnDefinition = "付费标志")
    private String RCM_PAYFLAG;

    @Column(nullable = false, columnDefinition = "推荐日期")
    private String RCM_DATE;

    @Column(nullable = false, columnDefinition = "推荐时间")
    private String RCM_TIME;

    @Column(nullable = false, columnDefinition = "推荐内容")
    private String RCM_CONTENT;

    @Column(nullable = false, columnDefinition = "推单结果")
    private String RCM_RESULT;

    @Column(nullable = false, columnDefinition = "赛事开始日期")
    private String EVE_STARTDATE;

    @Column(nullable = false, columnDefinition = "赛事开始时间")
    private String EVE_STARTTIME;

    @Column(nullable = false, columnDefinition = "赛事状态")
    private String EVE_STATUS;

    @Column(nullable = false, columnDefinition = "联赛种类")
    private String EVE_LEAGUETYPE;

    @Column(nullable = false, columnDefinition = "归属球种")
    private String EVE_BALLTYPE;

    @Column(nullable = false, columnDefinition = "主队")
    private String EVE_HOMETEAM;

    @Column(nullable = false, columnDefinition = "客队")
    private String EVE_VISITTEAM;

    @Column(nullable = false, columnDefinition = "赛果")
    private String EVE_RESULT;
}
