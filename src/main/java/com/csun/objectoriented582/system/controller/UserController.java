package com.csun.objectoriented582.system.controller;

import cn.hutool.core.map.MapUtil;
import com.csun.objectoriented582.common.CodeMsg;
import com.csun.objectoriented582.common.Constant;
import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.system.dto.PasswordDto;
import com.csun.objectoriented582.system.entity.User;
import com.csun.objectoriented582.system.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 我的公众号：MarkerHub
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        return new Result<>(userService.findUserById(id));
    }

    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public Result info(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        Assert.notNull(user, "administrator does not exist");
        return Result.success(user);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public Result list(String username) {
        Page<User> pageDate = userService.findAll(username, getPage());
        return Result.success(pageDate.getContent());
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public Result save(@Validated @RequestBody User user) {
        user.setCreated(LocalDateTime.now());
        user.setStatus(Constant.STATUS_ON);
        user.setPassword(passwordEncoder.encode(Constant.DEFULT_PASSWORD));
        user.setAvatar(Constant.DEFULT_AVATAR);
        userService.save(user);
        return Result.success(user);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:user:update')")
    public Result update(@Validated @RequestBody User user) {
        user.setUpdated(LocalDateTime.now());
        userService.save(user);
        return Result.success(user);
    }

    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public Result delete(@RequestBody Long[] ids) {
        userService.removeByIds(Arrays.asList(ids));
        userRoleService.remove(Arrays.asList(ids));
        return Result.success("");
    }

    @Transactional
    @PostMapping("/role/{userId}")
    @PreAuthorize("hasAuthority('sys:user:role')")
    public Result rolePerm(@PathVariable("userId") Long userId, @RequestBody Long[] roleIds) {
        List<UserRole> userRoles = new ArrayList<>();
        Arrays.stream(roleIds).forEach(r -> {
            UserRole sysUserRole = new UserRole();
            sysUserRole.setRoleId(r);
            sysUserRole.setUserId(userId);
            userRoles.add(sysUserRole);
        });
        List<Long> ids = new ArrayList<>();
        ids.add(userId);
        userRoleService.remove(ids);
        userRoleService.saveBatch(userRoles);
        return Result.success("");
    }

    @PostMapping("/repass")
    @PreAuthorize("hasAuthority('sys:user:repass')")
    public Result repass(@RequestBody Long userId) {
        User user = userService.findUserById(userId);
        user.setPassword(passwordEncoder.encode(Constant.DEFULT_PASSWORD));
        user.setUpdated(LocalDateTime.now());
        userService.save(user);
        return Result.success("");
    }

    @PostMapping("/updatePass")
    public Result updatePass(@Validated @RequestBody PasswordDto passwordDto, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        boolean matches = passwordEncoder.matches(passwordDto.getCurrentPass(), user.getPassword());
        if (!matches) {
            CodeMsg codeMsg = CodeMsg.SERVER_ERROR;
            codeMsg.setMsg("old password is wrong");
            return Result.error(codeMsg);
        }
        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
        user.setUpdated(LocalDateTime.now());
        userService.save(user);
        return Result.success("");
    }

    @PostMapping("/signup")
    public Result signUp(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "email") String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(roleService.findAll());
        user.setStatus(Constant.STATUS_ON);
        user.setAvatar(Constant.DEFULT_AVATAR);
        user.setCreated(LocalDateTime.now());
        userService.save(user);
        Map map = MapUtil.builder().put("username",username).put("password",password).map();
        return Result.success(map);
    }
}



