package com.csun.objectoriented582.system.service.impljpa;

import com.csun.objectoriented582.system.dto.MenuDto;
import com.csun.objectoriented582.system.entity.Menu;
import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.entity.RoleMenu;
import com.csun.objectoriented582.system.entity.User;
import com.csun.objectoriented582.system.repository.MenuRepository;
import com.csun.objectoriented582.system.repository.RoleMenuRepository;
import com.csun.objectoriented582.system.repository.UserRepository;
import com.csun.objectoriented582.system.service.MenuService;
import com.csun.objectoriented582.system.service.RoleMenuService;
import com.csun.objectoriented582.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    public RoleMenuRepository roleMenuRepository;

    @Override
    public void deleteByMenuId(Long id) {
        roleMenuRepository.deleteByMenuId(id);
    }

    @Override
    public List<RoleMenu> findAllByRoleId(Long roleId) {
        return roleMenuRepository.findAllByRoleId(roleId);
    }

    @Override
    public void remove(List<Long> ids) {
        roleMenuRepository.deleteByRoleId(ids);
    }

    public void saveBatch(List<RoleMenu> roleMenus) {
        roleMenuRepository.saveAll(roleMenus);
    }
}
