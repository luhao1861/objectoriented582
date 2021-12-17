package com.csun.objectoriented582.library.service;

import com.csun.objectoriented582.library.entity.Book;
import com.csun.objectoriented582.library.entity.Bookshelf;
import com.csun.objectoriented582.library.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book save(Book Book);

    Book findById(Long id);

    List<Book> findAll();

    Page<Book> findAll(String bookName, Pageable pageable);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);

    List<Book> findAllByBookshelf(Bookshelf bookshelf);
}
