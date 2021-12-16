package com.csun.objectoriented582.system.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @team mackie Studio
 * @Author QQ:15577969
 * @Date 2021-12-11 02:46:31
 */
@Entity
@Table(name = "sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1642783246091733558L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
    @Column(name = "parent_id")
    @NotNull(message = "parent ID can not be null")
    private Long parentId;

    @Column(name = "name")
    @NotBlank(message = "menu name can not be null")
    private String name;

    /**
     * 菜单URL
     */
    @Column(name = "path")
    private String path;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @Column(name = "perms")
    @NotBlank(message = "menu perms can not be null")
    private String perms;

    @Column(name = "component")
    private String component;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    @Column(name = "type")
    @NotNull(message = "menu type can not be null")
    private Long type;

    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Long orderNum;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "status")
    private Long status;

    @Transient
    private List<Menu> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }


    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }


    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }


    public LocalDateTime  getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime  created) {
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

}
