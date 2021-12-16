package com.csun.objectoriented582.system.repository;

import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);

    Page<User> findAll(Specification<User> spec, Pageable pageable);

}
