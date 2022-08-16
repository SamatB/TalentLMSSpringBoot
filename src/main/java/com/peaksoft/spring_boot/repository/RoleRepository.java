package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.Company;
import com.peaksoft.spring_boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
