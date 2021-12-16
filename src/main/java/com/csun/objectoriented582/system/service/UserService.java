package com.csun.objectoriented582.system.service;

import com.csun.objectoriented582.system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    User findUserById(Long id);

    User findUserByUsername(String username);

    String getUserAuthorityInfo(Long id);

    User getByUsername(String username);

    List<User> findAll();

    void save(User user);

    Page<User> findAll(String userName, Pageable pageable);

    void removeByIds(List<Long> ids);
}
