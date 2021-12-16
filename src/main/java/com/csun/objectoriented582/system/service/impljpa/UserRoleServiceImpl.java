package com.csun.objectoriented582.system.service.impljpa;

import com.csun.objectoriented582.system.entity.UserRole;
import com.csun.objectoriented582.system.repository.RoleMenuRepository;
import com.csun.objectoriented582.system.repository.UserRoleRepository;
import com.csun.objectoriented582.system.service.RoleMenuService;
import com.csun.objectoriented582.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    public UserRoleRepository userRoleRepository;

    @Override
    public void remove(List<Long> roleIds) {
        userRoleRepository.deleteByRoleId(roleIds);
    }

    @Override
    public void saveBatch(List<UserRole> userRoles) {
        userRoleRepository.saveAll(userRoles);
    }
}
