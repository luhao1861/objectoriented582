package com.csun.objectoriented582.library.repository;

import com.csun.objectoriented582.library.entity.Book;
import com.csun.objectoriented582.library.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Page<Room> findAll(Specification<Room> specification, Pageable pageableJPA);
}
