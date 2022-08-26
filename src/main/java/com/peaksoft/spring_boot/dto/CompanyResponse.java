package com.peaksoft.spring_boot.dto;

import com.peaksoft.spring_boot.entity.Course;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CompanyResponse {

    private Long id;
    private String name;
    private String locatedCountry;
    private LocalDate created;
    private List<Course> course;
}
