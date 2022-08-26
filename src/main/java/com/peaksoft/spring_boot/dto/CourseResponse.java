package com.peaksoft.spring_boot.dto;

import com.peaksoft.spring_boot.entity.Company;
import com.peaksoft.spring_boot.entity.Group;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CourseResponse {

    private Long id;
    private String courseName;
    private Long durationMonth;
    private Company company;
    private LocalDate created;
}
