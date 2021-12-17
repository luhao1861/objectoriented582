package com.csun.objectoriented582.library.controller;

import com.csun.objectoriented582.common.Constant;
import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.library.entity.Book;
import com.csun.objectoriented582.library.entity.Book;
import com.csun.objectoriented582.library.entity.Bookshelf;
import com.csun.objectoriented582.library.service.BookService;
import com.csun.objectoriented582.library.service.BookshelfService;
import com.csun.objectoriented582.library.service.RoomService;
import com.csun.objectoriented582.system.controller.BaseController;
import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hao Lu
 */
@RestController
@RequestMapping("/library/book")
public class BookController extends BaseController {

    @Autowired
    BookService bookService;

    @Autowired
    BookshelfService bookshelfService;

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        return Result.success(bookService.findById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Book book, @RequestParam(name = "bookshelfId", required = true) Long id) {
        book.setCreated(LocalDateTime.now());
        Bookshelf bookshelf = bookshelfService.findById(id);
        book.setBookshelf(bookshelf);
        return Result.success(bookService.save(book));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Book book, @RequestParam(name = "bookshelfId", required = true) Long id) {
        book.setUpdated(LocalDateTime.now());
        Bookshelf bookshelf = bookshelfService.findById(id);
        book.setBookshelf(bookshelf);
        return Result.success(bookService.save(book));
    }

    @GetMapping("/list")
    public Result list(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "treeId", required = false) Long treeId) {
        Page<Book> pageList;
        if (treeId != null) {
            Bookshelf bookshelf = bookshelfService.findById(treeId);
            List<Book> bookshelfList = bookService.findAllByBookshelf(bookshelf);
            pageList = new PageImpl(bookshelfList, getPage(), bookshelfList.size());
        } else {
            pageList = bookService.findAll(code, getPage());
        }
        return Result.success(pageList);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        bookService.deleteByIds(ids);
        return Result.success("delete success");
    }

}