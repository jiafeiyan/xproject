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
                @Index(name = "Board_Index",columnList = "brdId")
        }
)
@Proxy(lazy = false)
@EntityListeners(AuditingEntityListener.class)
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brdId;

    @Column()
    @CreatedDate
    private String brdSetupdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐日期'")
    private String brdRcmdate;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐时间'")
    private String brdRcmtime;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐内容'")
    private String brdRcmcontent;

    @Column(nullable = true, columnDefinition = "varchar(255) COMMENT '推荐理由'")
    private String brdRcmreason;
}
