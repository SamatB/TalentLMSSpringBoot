package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.CompanyRequest;
import com.peaksoft.spring_boot.dto.CompanyResponse;
import com.peaksoft.spring_boot.service.CompanyService;
import com.peaksoft.spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
@Tag(name = "Company API", description = "User with role admin can get all companies, create, update, get by id, get by name, delete company or get size of students, " +
        "and with role teacher can get all companies, get company by id and name and get students' size")
public class CompanyController {
    private final CompanyService companyService;

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('TEACHER')")
    @Operation(summary = "get all companies", description = "we can get all companies")
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    @Operation(summary = "create company", description = "we can create company")
    public CompanyResponse create(@RequestBody CompanyRequest request) {
        return companyService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update company", description = "we can update company")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request) {
        return companyService.update(id, request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    @Operation(summary = "get company by id", description = "we can get company by id")
    public CompanyResponse getById(@PathVariable Long id) {
        return companyService.getById(id);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('TEACHER')")
    @Operation(summary = "get company by name", description = "we can get company by name")
    public CompanyResponse getByName(@PathVariable String name) {
        return companyService.getByName(name);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete company", description = "we can delete company")
    public ResponseEntity delete(@PathVariable Long id) {
        companyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/studentsSize")
    @PreAuthorize("hasAuthority('TEACHER')")
    @Operation(summary = "get students' size", description = "we can get the size of students")
    public ResponseEntity<Integer> size(@PathVariable Long id) {
        return new ResponseEntity<>(userService.sizeOfCompaniesStudents(id), HttpStatus.OK);
    }
}
