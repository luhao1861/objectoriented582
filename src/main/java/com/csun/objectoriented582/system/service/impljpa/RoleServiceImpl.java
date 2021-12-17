package com.csun.objectoriented582.system.service.impljpa;

import com.csun.objectoriented582.system.entity.Menu;
import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.repository.RoleRepository;
import com.csun.objectoriented582.system.service.RoleService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Menu> getMenuListByRoleList(Set<Role> roleList) {
        Set<Menu> menuList = new HashSet<>();
        for (Role role : roleList) {
            menuList.addAll(role.getMenusList());
        }
        return menuList;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Page<Role> findAll(String roleName, Pageable pageable) {
        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(roleName) || roleName != null) { //添加断言
                    Predicate likeRoleName = criteriaBuilder.like(root.get("name").as(String.class), roleName + "%");
                    predicates.add(likeRoleName);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        Pageable pageableJPA = PageRequest.of(pageable.getPageNumber() , pageable.getPageSize());
        return roleRepository.findAll(specification, pageableJPA);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void removeByIds(List<Long> asList) {
        roleRepository.deleteAllByIdInBatch(asList);
    }

    @Override
    public Set<Role> findAll() {
        List<Role> list = roleRepository.findAll();
        Set<Role> set = new HashSet<>(list);
        return set;
    }
}
