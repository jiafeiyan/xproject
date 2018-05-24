package com.zeus.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_Admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "varchar(255)")
    private String username;

    @Column(columnDefinition = "varchar(255)")
    private String Password;
}
