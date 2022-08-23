package com.peaksoft.spring_boot.dto;

import lombok.Data;

@Data
public class TeacherRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Long courseId;
    private String roleName;
}
