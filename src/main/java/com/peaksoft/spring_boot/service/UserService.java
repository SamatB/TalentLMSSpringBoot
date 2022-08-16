package com.peaksoft.spring_boot.service;

import com.peaksoft.spring_boot.dto.StudentRequest;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.dto.TeacherRequest;
import com.peaksoft.spring_boot.dto.TeacherResponse;
import com.peaksoft.spring_boot.entity.User;

import java.util.List;

public interface UserService {
    StudentResponse getStudentById(Long id);
    List<StudentResponse> getAllStudents();
    List<TeacherResponse> getAllTeachers();
    List<StudentResponse> getStudentByGroupId(Long groupId);
    StudentResponse getStudentByEmail(String email);
    TeacherResponse getTeacherByEmail(String email);
    StudentResponse register(StudentRequest request);
    TeacherResponse addTeacher(TeacherRequest request);
    StudentResponse updateStudent(Long id, StudentRequest request);
    TeacherResponse updateTeacher(Long id, TeacherRequest request);
    int sizeOfCompaniesStudents(Long companyId);
}
