package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.GroupRequest;
import com.peaksoft.spring_boot.dto.GroupResponse;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.entity.Group;
import com.peaksoft.spring_boot.service.GroupService;
import com.peaksoft.spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
@Tag(name = "Group API", description = "User with role admin, teacher can get all groups, " +
        "get group by name, create, update, delete group, or get students by group id")
public class GroupController {
    private final GroupService groupService;

    private final UserService userService;

    @GetMapping
    @Operation(summary = "get all groups", description = "we can get all groups")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping
    @Operation(summary = "create group", description = "we can create group")
    public GroupResponse create(@RequestBody GroupRequest request) {
        return groupService.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get group by id", description = "we can get group by id")
    public Group getById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @GetMapping("/{name}")
    @Operation(summary = "get group by name", description = "we can get group by name")
    public Group getByName(@PathVariable String name) {
        return groupService.getGroupByName(name);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update group", description = "we can update group")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request) {
        return groupService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete group", description = "we can delete group")
    public ResponseEntity delete(@PathVariable Long id) {
        groupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    @Operation(summary = "get students by group id", description = "we can get students by group id")
    public List<StudentResponse> getStudents(@PathVariable Long id) {
        return userService.getStudentByGroupId(id);
    }
}
