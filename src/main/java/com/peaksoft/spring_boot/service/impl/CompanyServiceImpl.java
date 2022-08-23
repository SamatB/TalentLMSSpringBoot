package com.peaksoft.spring_boot.service.impl;

import com.peaksoft.spring_boot.dto.CompanyRequest;
import com.peaksoft.spring_boot.dto.CompanyResponse;
import com.peaksoft.spring_boot.entity.Company;
import com.peaksoft.spring_boot.repository.CompanyRepository;
import com.peaksoft.spring_boot.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company: companies) {
            responses.add(mapToResponse(company));
        }
        return responses;
    }

    @Override
    public CompanyResponse create(CompanyRequest company) {
        Company company1 = new Company();
        company1.setCompanyName(company.getName());
        company1.setLocatedCountry(company.getLocatedCountry());
        companyRepository.save(company1);
        return mapToResponse(company1);
    }

    @Override
    public CompanyResponse getById(Long id) {
        return mapToResponse(companyRepository.findById(id).get());
    }

    @Override
    public CompanyResponse getByName(String name) {
        return mapToResponse(companyRepository.findByCompanyName(name));
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest company) {
        Company company1 = companyRepository.findById(id).get();
        company1.setCompanyName(company.getName());
        company1.setLocatedCountry(company.getLocatedCountry());
        companyRepository.save(company1);
        return mapToResponse(company1);
    }

    @Override
    public void deleteById(Long id) {
         companyRepository.deleteById(id);
    }

    private CompanyResponse mapToResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        return companyResponse;
    }
}
