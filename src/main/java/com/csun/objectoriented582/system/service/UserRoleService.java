package com.csun.objectoriented582.system.service;

import com.csun.objectoriented582.system.entity.RoleMenu;
import com.csun.objectoriented582.system.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    public void remove(List<Long> roleId);

    public void saveBatch(List<UserRole> roleMenus);
}
