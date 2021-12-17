package com.csun.objectoriented582.library.controller;

import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.library.dto.OptionDto;
import com.csun.objectoriented582.library.dto.TreeDto;
import com.csun.objectoriented582.library.entity.Bookshelf;
import com.csun.objectoriented582.library.entity.Room;
import com.csun.objectoriented582.library.service.BookshelfService;
import com.csun.objectoriented582.library.service.RoomService;
import com.csun.objectoriented582.system.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Hao Lu
 */
@RestController
@RequestMapping("/library/bookshelf")
public class BookshelfController extends BaseController {

    @Autowired
    BookshelfService bookshelfService;

    @Autowired
    RoomService roomService;

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        return Result.success(bookshelfService.findById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Bookshelf bookshelf, @RequestParam(name = "roomId", required = true) Long id) {
        bookshelf.setCreated(LocalDateTime.now());
        Room room = roomService.findById(id);
        bookshelf.setRoom(room);
        return Result.success(bookshelfService.save(bookshelf));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Bookshelf bookshelf, @RequestParam(name = "roomId", required = true) Long id) {
        bookshelf.setUpdated(LocalDateTime.now());
        Room room = roomService.findById(id);
        bookshelf.setRoom(room);
        return Result.success(bookshelfService.save(bookshelf));
    }

    @GetMapping("/list")
    public Result list(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "treeId", required = false) Long treeId) {
        Page<Bookshelf> pageList;
        if (treeId != null) {
            Room room = roomService.findById(treeId);
            List<Bookshelf> bookshelfList = bookshelfService.findAllByRoom(room);
            pageList = new PageImpl(bookshelfList, getPage(), bookshelfList.size());
        } else {
            pageList = bookshelfService.findAll(code, getPage());
        }
        return Result.success(pageList);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        bookshelfService.deleteByIds(ids);
        return Result.success("delete success");
    }

    @GetMapping("/options")
    public Result options() {
        List<OptionDto> bookshelfServiceOptions = bookshelfService.getOptions();
        return Result.success(bookshelfServiceOptions);
    }

    @GetMapping("/tree")
    public Result tree() {
        List<TreeDto> treeDtos = bookshelfService.buildTree();
        return Result.success(treeDtos);
    }
}