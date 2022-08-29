package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.dto.TeacherRequest;
import com.peaksoft.spring_boot.dto.TeacherResponse;
import com.peaksoft.spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Teacher API", description = "User with role admin can get all teachers, get teacher by email, create, update or delete teacher")
public class TeacherController {
    private final UserService userService;

    @GetMapping("/getAll")
    @Operation(summary = "get all teachers", description = "we can get all teachers")
    public List<TeacherResponse> getAllTeachers() {
        return userService.getAllTeachers();
    }

    @GetMapping
    @Operation(summary = "get all teachers", description = "we can get all teachers")
    public List<TeacherResponse> getAllTeachers(@RequestParam(name = "text", required = false) String text,
                                               @RequestParam int page,
                                               @RequestParam int size,
                                                @RequestParam LocalDate startDate,
                                                @RequestParam LocalDate endDate) {
        return userService.teacherPagination(text, page, size,startDate,endDate);
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

    @DeleteMapping("/{id}")
    @Operation(summary = "delete teacher", description = "we can delete teacher")
    public void deleteTeacher(@PathVariable Long id) {
        userService.deleteTeacher(id);
    }
}
