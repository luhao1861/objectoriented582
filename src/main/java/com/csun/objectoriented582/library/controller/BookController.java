package com.csun.objectoriented582.library.controller;

import com.csun.objectoriented582.common.Constant;
import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.library.service.BookService;
import com.csun.objectoriented582.system.controller.BaseController;
import com.csun.objectoriented582.system.entity.Role;
import com.csun.objectoriented582.system.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        return null;
    }

}