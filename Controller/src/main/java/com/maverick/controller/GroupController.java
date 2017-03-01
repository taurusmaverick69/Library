package com.maverick.controller;

import com.maverick.domain.Group;
import com.maverick.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/{id}")
    public Group findById(@PathVariable("id") int id) {
        return groupService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Group group) {
        groupService.save(group);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        groupService.delete(id);
    }
}