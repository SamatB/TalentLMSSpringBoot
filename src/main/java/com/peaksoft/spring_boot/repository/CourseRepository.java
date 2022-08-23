package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseName(String name);
}
