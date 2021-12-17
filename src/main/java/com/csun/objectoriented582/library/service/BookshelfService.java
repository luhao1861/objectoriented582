package com.csun.objectoriented582.library.service;

import com.csun.objectoriented582.library.dto.OptionDto;
import com.csun.objectoriented582.library.dto.TreeDto;
import com.csun.objectoriented582.library.entity.Bookshelf;
import com.csun.objectoriented582.library.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookshelfService {
    Bookshelf save(Bookshelf bookshelf);

    Bookshelf findById(Long id);

    List<Bookshelf> findAll();

    Page<Bookshelf> findAll(String code, Pageable pageable);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);

    List<Bookshelf> findAllByRoom(Room room);

    List<OptionDto> getOptions();

    List<TreeDto> buildTree();
}
