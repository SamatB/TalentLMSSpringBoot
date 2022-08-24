package com.peaksoft.spring_boot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GroupResponse {

    private String groupName;
    private LocalDate dateOfCreate;
    private String dateOfFinish;
//    private Long courseId;
}
