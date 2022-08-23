package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.CompanyRequest;
import com.peaksoft.spring_boot.dto.CourseRequest;
import com.peaksoft.spring_boot.dto.CourseResponse;
import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
@Tag(name = "Curse API", description = "User with role admin can get all courses, get course by id, get course by name,  create, update or delete course, " +
        "and with role teacher can get all courses, get course by id and name")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @PreAuthorize("hasAuthority('TEACHER')")
    @Operation(summary = "get all courses", description = "we can get all courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    @Operation(summary = "create course", description = "we can create course")
    public Course create(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update course", description = "we can update course")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request) {
        return courseService.updateCourse(id, request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    @Operation(summary = "get course by id", description = "we can get course by id")
    public Course getById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('TEACHER')")
    @Operation(summary = "get course by name", description = "we can get course by name")
    public Course getByName(@PathVariable String name) {
        return courseService.getCourseByName(name);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete course", description = "we can delete course")
    public ResponseEntity delete(@PathVariable Long id) {
        courseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
