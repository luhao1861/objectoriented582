package com.csun.objectoriented582.system.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @team csun
 * @Author HaoLu
 * @Date 2021-12-11 02:45:07
 */
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 4847013485573646271L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Role name can not be blank")
    private String name;

    @Column(name = "code")
    @NotBlank(message = "Role code can not be blank")
    private String code;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "status")
    private Long status;

    @ManyToMany(targetEntity = Menu.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_menu",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")}
    )
    private Set<Menu> menusList = new HashSet<>();

    @Transient
    private List<Long> menuIds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Menu> getMenusList() {
        return menusList;
    }

    public void setMenusList(Set<Menu> menusList) {
        this.menusList = menusList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }


    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
