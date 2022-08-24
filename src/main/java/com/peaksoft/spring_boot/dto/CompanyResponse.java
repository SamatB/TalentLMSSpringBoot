package com.peaksoft.spring_boot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyResponse {

    private Long id;
    private String name;
    private String locatedCountry;
    private LocalDate created;
}
