package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.StudentRequest;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.entity.User;
import com.peaksoft.spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
@Tag(name = "Student API", description = "User with role admin, teacher can get all students, create/add, " +
        "update, delete, get student by id or email")
public class StudentController {

    private final UserService userService;

//    @Override
//    public List<User> findByCreated(@Param LocalDate registerDate) {
//        return userService.findByCreated(registerDate);
//    }

    @GetMapping("/getAll")
    @Operation(summary = "get all students", description = "we can get all student")
    public List<StudentResponse> getAllStudents() {
        return userService.getAllStudents();
    }

    @GetMapping
    @Operation(summary = "get all students", description = "we can get all student")
    public List<StudentResponse> getAllStudent(@RequestParam(name = "text", required = false) String text,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        return userService.pagination(text, page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get student by id", description = "we can get student by id")
    public StudentResponse getById(@PathVariable Long id) {
        return userService.getStudentById(id);
    }

    @PostMapping
    @Operation(summary = "create student", description = "we can create/add student")
    public StudentResponse addStudent(@RequestBody StudentRequest request) {
        return userService.register(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update student", description = "we can update student")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return userService.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "delete student", description = "we can delete student")
    public void deleteStudent(@PathVariable Long id) {
        userService.deleteStudent(id);
    }

    @GetMapping("/{email}")
    @Operation(summary = "get student by email", description = "we can get student by email")
    public StudentResponse getByEmail(@PathVariable String email) {
        return userService.getStudentByEmail(email);
    }
}
