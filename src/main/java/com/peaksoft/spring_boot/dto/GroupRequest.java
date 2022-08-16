package com.peaksoft.spring_boot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GroupRequest {
    private String name;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private Long courseId;
}