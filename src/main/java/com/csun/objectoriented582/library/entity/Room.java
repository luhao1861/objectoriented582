package com.csun.objectoriented582.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @Author HaoLu
 * @Date 2021-12-14 18:33:44
 */
@Data
@Entity
@Table(name = "room")
public class Room implements Serializable {

    private static final long serialVersionUID = 2011723784571653532L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "remark")
    private String remark;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "status")
    private Long status;
}
