package com.csun.objectoriented582.system.repository;

import com.csun.objectoriented582.system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Modifying //说明该操作是修改类型操作，删除或者修改
    @Transactional //因为默认是readOnly=true的，这里必须自己进行声明
    @Query("delete from RoleMenu where roleId in (?1)") //删除的语句
    public void deleteByRoleId(List<Long> roleIds);

}
