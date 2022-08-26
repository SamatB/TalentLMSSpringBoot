package com.peaksoft.spring_boot.service.impl;

import com.peaksoft.spring_boot.dto.CourseRequest;
import com.peaksoft.spring_boot.dto.CourseResponse;
import com.peaksoft.spring_boot.entity.Company;
import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.entity.User;
import com.peaksoft.spring_boot.repository.CompanyRepository;
import com.peaksoft.spring_boot.repository.CourseRepository;
import com.peaksoft.spring_boot.repository.UserRepository;
import com.peaksoft.spring_boot.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(mapToResponse(course));
        }
        return courseResponses;
    }

    @Override
    public Course getCourseByName(String name) {
        return courseRepository.findByCourseName(name);
    }


    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public CourseResponse createCourse(CourseRequest course) {
        Course course1 = new Course();
        Company company = new Company();
        course1.setCourseName(course.getCourseName());
        course1.setDurationMonth(course.getDurationMonth());
        course1.setCreated(LocalDate.now());
        course1.setCompany(companyRepository.findById(course.getCompanyId()).get());
        course1.setCompanyId(course.getCompanyId());
        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        company.setCourses(courseList);
        courseRepository.save(course1);
        return mapToResponse(course1);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id).get();
        course.setCourseName(request.getCourseName());
        course.setDurationMonth(request.getDurationMonth());
        Company company = companyRepository.findById(request.getCompanyId()).get();
        course.setCompany(company);
        courseRepository.save(course);
        return mapToResponse(course);
    }

    @Override
    public void deleteById(Long id) {
     List<User>users=userRepository.getStudentsByCourseId(id);
     for (User user:users){
         user.setCourse(null);
         userRepository.save(user);
     }
        courseRepository.deleteById(id);
    }

    private CourseResponse mapToResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDurationMonth(course.getDurationMonth());
        courseResponse.setCompany(course.getCompany());
        courseResponse.setCreated(LocalDate.now());
        return courseResponse;
    }
}
