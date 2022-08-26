package com.peaksoft.spring_boot.service;

import com.peaksoft.spring_boot.dto.*;
import com.peaksoft.spring_boot.entity.User;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserService {
    StudentResponse getStudentById(Long id);

    List<StudentResponse> getAllStudents();

    List<TeacherResponse> getAllTeachers();

    List<StudentResponse> getStudentByGroupId(Long groupId);

    StudentResponse getStudentByEmail(String email);

    List<User> search(String name, Pageable pageable, LocalDate startDate, LocalDate endDate);

    TeacherResponse getTeacherByEmail(String email);

    StudentResponse register(StudentRequest request);
    UserResponse userRegister(UserRequest request);

    TeacherResponse addTeacher(TeacherRequest request);

    StudentResponse updateStudent(Long id, StudentRequest request);

    TeacherResponse updateTeacher(Long id, TeacherRequest request);

    void deleteStudent(Long id);

    void deleteTeacher(Long id);

    int sizeOfCompaniesStudents(Long companyId);

//    List<User> search(String name, Pageable pageable);

    List<StudentResponse> pagination(String text, int page, int size);

    List<TeacherResponse> teacherPagination(String text, int page, int size);
}
