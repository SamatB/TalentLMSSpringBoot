package com.peaksoft.spring_boot.service.impl;

import com.peaksoft.spring_boot.dto.CourseRequest;
import com.peaksoft.spring_boot.dto.CourseResponse;
import com.peaksoft.spring_boot.entity.Company;
import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.repository.CompanyRepository;
import com.peaksoft.spring_boot.repository.CourseRepository;
import com.peaksoft.spring_boot.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    private final CompanyRepository companyRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }


    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course createCourse(Course course) {
        Course course1 = new Course();
        course1.setCourseName(course.getCourseName());
        course1.setDurationMonth(course.getDurationMonth());
        return courseRepository.save(course1);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id).get();
        course.setCourseName(request.getName());
        course.setDurationMonth(request.getDurationMonth());
        Company company = companyRepository.findById(request.getCompanyId()).get();
        course.setCompany(company);
        courseRepository.save(course);
        return mapToResponse(course);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseResponse mapToResponse(Course course){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setName(course.getCourseName());
        courseResponse.setDurationMonth(course.getDurationMonth());
        return courseResponse;
    }
}
