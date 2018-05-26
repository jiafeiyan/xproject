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
        name = "t_Board",
        indexes = {
                @Index(name = "Board_Index",columnList = "BRD_ID")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BRD_ID;

    @Column()
    @CreatedDate
    private String BRD_SETUPDATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐日期'")
    private String BRD_RCMDATE;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐时间'")
    private String BRD_RCMTIME;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐内容'")
    private String BRD_RCMCONTENT;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '推荐理由'")
    private String BRD_RCMREASON;
}
