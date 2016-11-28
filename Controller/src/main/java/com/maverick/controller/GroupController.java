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

    @RequestMapping
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @RequestMapping("/{id}")
    public Group findById(@PathVariable("id") int id) {
        return groupService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@ModelAttribute("book") Group group) {
        groupService.save(group);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        groupService.delete(id);
    }

}
