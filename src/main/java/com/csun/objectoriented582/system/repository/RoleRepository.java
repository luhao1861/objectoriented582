package com.csun.objectoriented582.system.repository;

import com.csun.objectoriented582.system.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findAll(Pageable pageable);

    Page<Role> findAll(Specification<Role> spec, Pageable pageable);
}
