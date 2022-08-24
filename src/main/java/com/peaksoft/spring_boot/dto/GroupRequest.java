package com.peaksoft.spring_boot.dto;

import lombok.Data;

@Data
public class GroupRequest {

    private String groupName;
    private String dateOfFinish;
    private Long courseId;
}
