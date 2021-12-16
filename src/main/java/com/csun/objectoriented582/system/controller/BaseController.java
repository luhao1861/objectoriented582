package com.csun.objectoriented582.system.controller;

import com.csun.objectoriented582.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    public HttpServletRequest request;
    @Autowired
    public UserService userService;
    @Autowired
    public RoleService roleService;
    @Autowired
    public MenuService menuService;
    @Autowired
    public RoleMenuService roleMenuService;
    @Autowired
    public UserRoleService userRoleService;

//    @Autowired
//    public BaseController(HttpServletRequest request, UserService userService, RoleService roleService, MenuService menuService) {
//        this.request = request;
//        this.userService = userService;
//        this.roleService = roleService;
//        this.menuService = menuService;
//    }


    public BaseController() {
    }

    public Pageable getPage() {
        int current = ServletRequestUtils.getIntParameter(request, "current", 1) - 1;
        int size = ServletRequestUtils.getIntParameter(request, "size", 10);
        Pageable pageable = PageRequest.of(current, size);
        return pageable;
    }
}
