package com.peaksoft.spring_boot.dto;

import lombok.Data;

@Data
public class CourseRequest {

    private String courseName;
    private Long durationMonth;
    private Long companyId;
}
