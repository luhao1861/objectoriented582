package com.csun.objectoriented582.library.controller;

import com.csun.objectoriented582.common.Result;
import com.csun.objectoriented582.library.dto.OptionDto;
import com.csun.objectoriented582.library.dto.TreeDto;
import com.csun.objectoriented582.library.entity.Room;
import com.csun.objectoriented582.library.service.RoomService;
import com.csun.objectoriented582.system.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Hao Lu
 */
@RestController
@RequestMapping("/library/room")
public class RoomController extends BaseController {

    @Autowired
    RoomService roomService;

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        return Result.success(roomService.findById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Room room) {
        room.setCreated(LocalDateTime.now());
        return Result.success(roomService.save(room));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Room room) {
        room.setUpdated(LocalDateTime.now());
        return Result.success(roomService.save(room));
    }

    @GetMapping("/list")
    public Result list(@RequestParam(name = "roomNumber", required = false) String roomNumber) {
        Page<Room> rooms = roomService.findAll(roomNumber, getPage());
        return Result.success(rooms);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        roomService.deleteByIds(ids);
        return Result.success("delete success");
    }

    @GetMapping("/tree")
    public Result tree() {
        List<TreeDto> rooms = roomService.buildTree();
        return Result.success(rooms);
    }

    @GetMapping("/options")
    public Result options() {
        List<OptionDto> rooms = roomService.getOptions();
        return Result.success(rooms);
    }
}