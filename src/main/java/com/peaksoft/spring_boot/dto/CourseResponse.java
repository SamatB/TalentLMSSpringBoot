package com.peaksoft.spring_boot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseResponse {

    private Long id;
    private String courseName;
    private Long durationMonth;
    private LocalDate created;
}
