package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.StudentRequest;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
@Tag(name = "Student API", description = "User with role admin, editor can get all students, create/add, " +
        "update student get student by id, group id or email")
public class StudentController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "get all students", description = "we can get all student")
    public List<StudentResponse> responses() {
        return userService.getAllStudents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get student by id", description = "we can get student by id")
    public StudentResponse getById(@PathVariable Long id) {
        return userService.getStudentById(id);
    }

    @PostMapping
    @Operation(summary = "create student", description = "we can create/add student")
    public StudentResponse addStudent (@RequestBody StudentRequest request) {
        return userService.register(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update student", description = "we can update student")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return userService.updateStudent(id, request);
    }

    @GetMapping("/{email}")
    @Operation(summary = "get student by email", description = "we can get student by email")
    public StudentResponse getByEmail(@PathVariable String email) {
        return userService.getStudentByEmail(email);
    }

}