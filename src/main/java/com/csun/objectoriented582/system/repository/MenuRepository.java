package com.csun.objectoriented582.system.repository;

import com.csun.objectoriented582.system.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    Long countByParentId(Long parentId);
}
