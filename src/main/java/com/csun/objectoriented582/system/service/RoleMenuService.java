package com.csun.objectoriented582.system.service;

import com.csun.objectoriented582.system.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService {
    void deleteByMenuId(Long id);

    List<RoleMenu> findAllByRoleId(Long roleId);

    void remove(List<Long> asList);

    public void saveBatch(List<RoleMenu> roleMenus);
}
