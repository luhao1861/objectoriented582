package com.csun.objectoriented582.system.controller;

import com.csun.objectoriented582.common.Constant;
import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.entity.RoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hao Lu
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        Role role = roleService.findById(id);
        List<RoleMenu> roleMenus = roleMenuService.findAllByRoleId(role.getId());
        List<Long> menuIds = roleMenus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
        role.setMenuIds(menuIds);
        return Result.success(role);
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/list")
    public Result list(String name) {
        Page<Role> roles = roleService.findAll(name, getPage());
        return Result.success(roles.getContent());
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:role:save')")
    public Result save(@Validated @RequestBody Role role) {
        role.setCreated(LocalDateTime.now());
        role.setStatus(Constant.STATUS_ON);
        roleService.save(role);
        return Result.success(role);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result update(@Validated @RequestBody Role role) {
        role.setUpdated(LocalDateTime.now());
        roleService.save(role);
        return Result.success(role);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @Transactional
    public Result info(@RequestBody Long[] ids) {
        roleService.removeByIds(Arrays.asList(ids));
        userRoleService.remove(Arrays.asList(ids));
        roleMenuService.remove(Arrays.asList(ids));
        return Result.success("");
    }

    @Transactional
    @PostMapping("/perm/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:perm')")
    public Result info(@PathVariable("roleId") Long roleId, @RequestBody Long[] menuIds) {
        List<RoleMenu> roleMenus = new ArrayList<>();
        Arrays.stream(menuIds).forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenus.add(roleMenu);
        });
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        roleMenuService.remove(roleIds);
        roleMenuService.saveBatch(roleMenus);
        return Result.success(menuIds);
    }

}