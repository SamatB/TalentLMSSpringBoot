package com.peaksoft.spring_boot.service;

import com.peaksoft.spring_boot.dto.CourseRequest;
import com.peaksoft.spring_boot.dto.CourseResponse;
import com.peaksoft.spring_boot.entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourses();

    Course getCourseByName(String name);

    Course getCourseById(Long id);

    CourseResponse createCourse(CourseRequest request);

    CourseResponse updateCourse(Long id, CourseRequest course);

    void deleteById(Long id);
}
