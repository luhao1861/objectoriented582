package com.csun.objectoriented582.library.service.impl;

import com.csun.objectoriented582.library.dto.OptionDto;
import com.csun.objectoriented582.library.dto.TreeDto;
import com.csun.objectoriented582.library.entity.Bookshelf;
import com.csun.objectoriented582.library.entity.Room;
import com.csun.objectoriented582.library.repository.BookshelfRepository;
import com.csun.objectoriented582.library.service.BookshelfService;
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
public class BookshelfServiceImpl implements BookshelfService {

    @Autowired
    BookshelfRepository bookshelfRepository;

    @Override
    public Bookshelf save(Bookshelf bookshelf) {
        return bookshelfRepository.save(bookshelf);
    }

    @Override
    public Bookshelf findById(Long id) {
        return bookshelfRepository.findById(id).get();
    }

    @Override
    public List<Bookshelf> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "bookshelfCode");
        return bookshelfRepository.findAll(sort);
    }

    @Override
    public Page<Bookshelf> findAll(String code, Pageable pageable) {
        Specification<Bookshelf> specification = new Specification<Bookshelf>() {
            @Override
            public Predicate toPredicate(Root<Bookshelf> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(code) || code != null) { //添加断言
                    Predicate likeRoleName = criteriaBuilder.like(root.get("bookshelfCode").as(String.class), code + "%");
                    predicates.add(likeRoleName);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        Pageable pageableJPA = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return bookshelfRepository.findAll(specification, pageableJPA);
    }

    @Override
    public void deleteById(Long id) {
        bookshelfRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        bookshelfRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public List<Bookshelf> findAllByRoom(Room room) {
        return bookshelfRepository.findAllByRoom(room);
    }

    @Override
    public List<OptionDto> getOptions() {
        List<OptionDto> list = new ArrayList<>();
        List<Bookshelf> roomList = bookshelfRepository.findAll();
        for (Bookshelf bookshelf : roomList) {
            OptionDto dto = new OptionDto();
            dto.setValue(bookshelf.getId());
            dto.setLabel("Bookshelf Code_" + bookshelf.getBookshelfCode());
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<TreeDto> buildTree() {
        List<TreeDto> list = new ArrayList<>();
        List<Bookshelf> bookshelfList = bookshelfRepository.findAll();
        for (Bookshelf bookshelf : bookshelfList) {
            TreeDto dto = new TreeDto();
            dto.setId(bookshelf.getId());
            dto.setLabel("Bookshelf Code _ " + bookshelf.getBookshelfCode());
            list.add(dto);
        }
        return list;
    }
}
