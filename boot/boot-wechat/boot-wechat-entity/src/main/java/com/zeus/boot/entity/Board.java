package com.zeus.boot.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(
        name = "t_Board",
        indexes = {
                @Index(name = "Board_Index",columnList = "BRD_ID")
        }
)
@Proxy(lazy = false)
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "varchar(255) COMMENT '公告ID'")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BRD_ID;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '创建日期'")
    private String BRD_SETUPDATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '创建时间'")
    private String BRD_SETUPTIME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐日期'")
    private String BRD_RCMDATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐时间'")
    private String BRD_RCMTIME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐内容'")
    private String BRD_RCMCONTENT;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐理由'")
    private String BRD_RCMREASON;
}
