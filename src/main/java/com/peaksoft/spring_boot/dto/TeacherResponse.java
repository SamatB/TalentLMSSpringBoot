package com.peaksoft.spring_boot.dto;

import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.entity.Role;
import com.peaksoft.spring_boot.entity.StudyFormat;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResponse {

    private String email;
    private String firstName;
    private String lastname;
    private Course course;
    private List<Role> roles;
}
