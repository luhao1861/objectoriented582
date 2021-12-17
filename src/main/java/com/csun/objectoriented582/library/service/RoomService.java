package com.csun.objectoriented582.library.service;

import com.csun.objectoriented582.library.dto.OptionDto;
import com.csun.objectoriented582.library.dto.TreeDto;
import com.csun.objectoriented582.library.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {
    Room save(Room room);

    Room findById(Long id);

    List<Room> findAll();

    Page<Room> findAll(String roomNumber, Pageable pageable);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);

    List<TreeDto> buildTree();

    List<OptionDto> getOptions();
}
