package com.csun.objectoriented582.system.service.impljpa;

import com.csun.objectoriented582.system.entity.Menu;
import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.entity.User;
import com.csun.objectoriented582.system.repository.UserRepository;
import com.csun.objectoriented582.system.service.RoleService;
import com.csun.objectoriented582.system.service.UserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    public UserRepository userRepository;
    public RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {

        User user = userRepository.findById(userId).get();
        String authority = "";
        Set<Role> roles = userRepository.findById(userId).get().getRoles();
        if (roles.size() > 0) {
            String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
            authority = roleCodes.concat(",");
        }
        Set<Menu> menus = roleService.getMenuListByRoleList(roles);
        if (menus.size() > 0) {
            String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));
            authority = authority.concat(menuPerms);
        }
        return authority;
    }

    @Override
    public User getByUsername(String username) {
        return findUserByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAll(String userName, Pageable pageable) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(userName) || userName != null) { //添加断言
                    Predicate likeUserName = criteriaBuilder.like(root.get("username").as(String.class), userName + "%");
                    predicates.add(likeUserName);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        Pageable pageableJPA = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return userRepository.findAll(specification, pageableJPA);
    }

    @Override
    public void removeByIds(List<Long> ids) {
        userRepository.deleteAllByIdInBatch(ids);
    }
}
