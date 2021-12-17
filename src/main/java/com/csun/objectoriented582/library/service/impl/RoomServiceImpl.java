package com.csun.objectoriented582.library.service.impl;

import com.csun.objectoriented582.library.dto.OptionDto;
import com.csun.objectoriented582.library.dto.TreeDto;
import com.csun.objectoriented582.library.entity.Room;
import com.csun.objectoriented582.library.repository.RoomRepository;
import com.csun.objectoriented582.library.service.RoomService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public List<Room> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "roomNumber");
        return roomRepository.findAll(sort);
    }

    @Override
    public Page<Room> findAll(String roomNumber, Pageable pageable) {
        Specification<Room> specification = new Specification<Room>() {
            @Override
            public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(roomNumber) || roomNumber != null) { //添加断言
                    Predicate likeRoleName = criteriaBuilder.like(root.get("roomNumber").as(String.class), roomNumber + "%");
                    predicates.add(likeRoleName);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        Pageable pageableJPA = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return roomRepository.findAll(specification, pageableJPA);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        roomRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public List<TreeDto> buildTree() {
        List<TreeDto> list = new ArrayList<>();
        List<Room> roomList = roomRepository.findAll();
        for (Room room : roomList) {
            TreeDto dto = new TreeDto();
            dto.setId(room.getId());
            dto.setLabel("Room Number_" + room.getRoomNumber());
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<OptionDto> getOptions() {
        List<OptionDto> list = new ArrayList<>();
        List<Room> roomList = roomRepository.findAll();
        for (Room room : roomList) {
            OptionDto dto = new OptionDto();
            dto.setValue(room.getId());
            dto.setLabel("Room Number_" + room.getRoomNumber());
            list.add(dto);
        }
        return list;
    }
}
