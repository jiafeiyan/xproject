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
                @Index(name = "Board_Index",columnList = "brd_id")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brd_id;

    @Column()
    @CreatedDate
    private String brd_setupdate;

    @Column()
    @CreatedDate
    private String brd_setuptime;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐日期'")
    private String brd_rcmdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐时间'")
    private String brd_rcmtime;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐内容'")
    private String brd_rcmcontent;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐理由'")
    private String brd_rcmreason;
}
