package com.csun.objectoriented582.system.service.impljpa;

import com.csun.objectoriented582.system.dto.MenuDto;
import com.csun.objectoriented582.system.entity.Menu;
import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.entity.User;
import com.csun.objectoriented582.system.repository.MenuRepository;
import com.csun.objectoriented582.system.repository.UserRepository;
import com.csun.objectoriented582.system.service.MenuService;
import com.csun.objectoriented582.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    public MenuRepository menuRepository;
    public UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<MenuDto> getCurrentUserMenu() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByUsername(username);
        Set<Role> roles = userRepository.findById(user.getId()).get().getRoles();
        Set<Menu> menus = roleService.getMenuListByRoleList(roles);
        List<Menu> menuTree = buildTreeMenu(menus);
        return convert(menuTree);
    }

    @Override
    public Menu findById(Long id) {
        return menuRepository.findById(id).get();
    }

    @Override
    public List<Menu> tree() {
        List<Menu> menuList = menuRepository.findAll(Sort.by(Sort.Direction.ASC,"orderNum"));
        return buildTreeMenu(menuList);
    }

    @Override
    public Menu save(Menu menu) {
       return menuRepository.save(menu);
    }

    @Override
    public boolean delete(Long id) {
        menuRepository.deleteById(id);
        return true;
    }

    @Override
    public Long countByParentId(Long parentId) {
        return menuRepository.countByParentId(parentId);
    }

    private List<Menu> buildTreeMenu(Collection<Menu> menus) {
        List<Menu> menusList = new ArrayList<>();
        for (Menu menu : menus) {
            for (Menu m : menus) {
                if (menu.getId() == m.getParentId()) {
                    menu.getChildren().add(m);
                }
            }
            if (menu.getParentId() == 0L) {
                menusList.add(menu);
            }
        }
        Collections.sort(menusList,(Menu m1,Menu m2)-> m1.getOrderNum().compareTo(m2.getOrderNum()));
        return menusList;
    }

    private List<MenuDto> convert(List<Menu> menuTree) {
        List<MenuDto> menuDtoList = new ArrayList<>();
        menuTree.forEach(menu -> {
            MenuDto dto = new MenuDto();
            dto.setId(menu.getId());
            dto.setName(menu.getPerms());
            dto.setTitle(menu.getName());
            dto.setComponent(menu.getComponent());
            dto.setPath(menu.getPath());
            if (menu.getChildren().size() > 0) {
                Collections.sort(menu.getChildren(),(Menu m1,Menu m2)-> m1.getOrderNum().compareTo(m2.getOrderNum()));
                dto.setChildren(convert(menu.getChildren()));
            }
            menuDtoList.add(dto);
        });
        return menuDtoList;
    }
}
