package com.peaksoft.spring_boot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class StudentRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String studyFormat;
    private Long groupId;
    private String roleName;
}
