package com.peaksoft.spring_boot.dto;

import com.peaksoft.spring_boot.entity.Course;
import com.peaksoft.spring_boot.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GroupResponse {

    private Long id;
    private String groupName;
    private LocalDate dateOfCreate;
    private String dateOfFinish;
    private List<Course> courses;
}
