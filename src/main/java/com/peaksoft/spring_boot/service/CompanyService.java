package com.peaksoft.spring_boot.service;

import com.peaksoft.spring_boot.dto.CompanyRequest;
import com.peaksoft.spring_boot.dto.CompanyResponse;
import com.peaksoft.spring_boot.entity.Company;
import com.peaksoft.spring_boot.repository.CompanyRepository;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> getAllCompanies();
    CompanyResponse create(CompanyRequest company);
    CompanyResponse getById(Long id);
    CompanyResponse getByName(String name);
    CompanyResponse update(Long id, CompanyRequest company);
    void deleteById(Long id);
}

