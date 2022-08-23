package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByGroupName(String name);
}
