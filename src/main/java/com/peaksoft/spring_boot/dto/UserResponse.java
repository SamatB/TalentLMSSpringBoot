package com.peaksoft.spring_boot.dto;

import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastname;
}
