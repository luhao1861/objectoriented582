package com.csun.objectoriented582.system.controller;

import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/test")
    public Result test() {
        return new Result(userService.findAll());
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/test/pass")
    public Result pass() {

        String password = bCryptPasswordEncoder.encode("111111");

        boolean matches = bCryptPasswordEncoder.matches("111111", password);

        System.out.println("matching result：" + matches);

        return Result.success(password);
    }

    @GetMapping("/test/userrole")
    public Result userrole() {
        Set list = userService.findUserById(1L).getRoles();
        return null;
    }
}
