package com.zeus.boot.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_Admin")
@Proxy(lazy = false)
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "varchar(255)")
    private String username;

    @Column(columnDefinition = "varchar(255)")
    private String Password;
}
