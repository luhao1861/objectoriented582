package com.csun.objectoriented582.system.controller;

import cn.hutool.core.map.MapUtil;
import com.csun.objectoriented582.common.CodeMsg;
import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.system.dto.MenuDto;
import com.csun.objectoriented582.system.entity.Menu;
import com.csun.objectoriented582.system.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

    @GetMapping("/nav")
    public Result nav(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        String authority = userService.getUserAuthorityInfo(user.getId());
        String[] authorityArr = StringUtils.tokenizeToStringArray(authority, ",");
        List<MenuDto> menuDtoList = menuService.getCurrentUserMenu();
        return Result.success(MapUtil.builder().put("authorization", authorityArr).put("nav", menuDtoList).map());
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable(name = "id") Long id){
        return Result.success(menuService.findById(id));
    }

    @GetMapping("/list")
    public Result list(){
        List<Menu>  menuList = menuService.tree();
        return Result.success(menuList);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody Menu menu){
        menu.setCreated(LocalDateTime.now());
        menuService.save(menu);
        return Result.success(menu);
    }

    @PostMapping("/update")
    public Result update(@Validated @RequestBody Menu menu){
        menu.setUpdated(LocalDateTime.now());
        return Result.success(menuService.save(menu));
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id){
        Long count = menuService.countByParentId(id);
        if (count > 0) {
            CodeMsg msg = CodeMsg.SERVER_ERROR;
            msg.setMsg("please delete submenu first");
            return Result.error(msg);
        }
        menuService.delete(id);
        // 同步删除中间关联表
        roleMenuService.deleteByMenuId(id);
        return Result.success("");
    }
}