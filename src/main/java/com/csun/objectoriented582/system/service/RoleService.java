package com.csun.objectoriented582.system.service;

import com.csun.objectoriented582.system.entity.Menu;
import com.csun.objectoriented582.system.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Menu> getMenuListByRoleList(Set<Role> roleList);

    Role findById(Long id);

    Page<Role> findAll(Pageable pageable);

    Page<Role> findAll(String roleName, Pageable pageable);

    void save(Role role);

    void removeByIds(List<Long> asList);

    Set<Role> findAll();
}
