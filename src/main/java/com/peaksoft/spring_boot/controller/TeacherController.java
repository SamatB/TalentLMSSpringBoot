package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.TeacherRequest;
import com.peaksoft.spring_boot.dto.TeacherResponse;
import com.peaksoft.spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Teacher API", description = "User with role admin can get all teachers, get teacher by email,  create or update teacher")
public class TeacherController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "get all teachers", description = "we can get all teachers")
    public List<TeacherResponse> getAllTeachers() {
        return userService.getAllTeachers();
    }

    @GetMapping("/{email}")
    @Operation(summary = "get teacher by email", description = "we can get teacher by email")
    public TeacherResponse  getByEmail(@PathVariable String email) {
        return userService.getTeacherByEmail(email);
    }

    @PostMapping
    @Operation(summary = "create teacher", description = "we can create/add teacher")
    public TeacherResponse create(@RequestBody TeacherRequest request) {
        return userService.addTeacher(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update teacher", description = "we can update teacher")
    public TeacherResponse update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        return userService.updateTeacher(id, request);
    }
}
