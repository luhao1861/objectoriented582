package com.csun.objectoriented582.system.repository;

import com.csun.objectoriented582.system.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu,Long> {
    @Modifying
    @Transactional
    @Query("delete from RoleMenu where menuId=?1")
    public void deleteByMenuId(Long menuId);

    @Modifying
    @Transactional
    @Query("delete from RoleMenu where roleId in (?1)")
    public void deleteByRoleId(List<Long> roleIds);

    public List<RoleMenu> findAllByRoleId(Long roleId);

}
