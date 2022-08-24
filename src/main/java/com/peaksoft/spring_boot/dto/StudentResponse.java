package com.peaksoft.spring_boot.dto;

import com.peaksoft.spring_boot.entity.Group;
import com.peaksoft.spring_boot.entity.Status;
import com.peaksoft.spring_boot.entity.StudyFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Data
@Getter
@Setter
public class StudentResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private StudyFormat studyFormat;
    private Status status;
    private LocalDate created;
}
