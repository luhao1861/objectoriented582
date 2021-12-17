package com.csun.objectoriented582.library.service.impl;

import com.csun.objectoriented582.library.entity.Book;
import com.csun.objectoriented582.library.entity.Bookshelf;
import com.csun.objectoriented582.library.repository.BookRepository;
import com.csun.objectoriented582.library.repository.BookshelfRepository;
import com.csun.objectoriented582.library.service.BookService;
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
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookshelfRepository bookshelfRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "bookCode");
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<Book> findAll(String bookName, Pageable pageable) {
        Specification<Book> specification = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(bookName) || bookName != null) { //添加断言
                    Predicate likeRoleName = criteriaBuilder.like(root.get("bookName").as(String.class), bookName + "%");
                    predicates.add(likeRoleName);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
        Pageable pageableJPA = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return bookRepository.findAll(specification, pageableJPA);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        bookRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public List<Book> findAllByBookshelf(Bookshelf bookshelf) {
        return bookRepository.findAllByBookshelf(bookshelf);
    }
}
