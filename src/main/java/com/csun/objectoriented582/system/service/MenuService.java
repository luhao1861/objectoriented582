package com.csun.objectoriented582.system.service;

import com.csun.objectoriented582.system.dto.MenuDto;
import com.csun.objectoriented582.system.entity.Menu;

import java.util.List;

public interface MenuService {
    List<MenuDto> getCurrentUserMenu();

    Menu findById(Long id);

    List<Menu> tree();

    Menu save(Menu menu);

    boolean delete(Long id);

    Long countByParentId(Long parentId);
}
