package com.peaksoft.spring_boot.dto;

import lombok.Data;

@Data
public class CourseRequest {
    private String name;
    private Long durationMonth;
    private Long companyId;
}
