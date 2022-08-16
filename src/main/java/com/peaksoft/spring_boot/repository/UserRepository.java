package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("select u from User u join Group g on u.group.id=g.id where g.id=?1")
    List<User> getStudentsByGroupId(Long id);

    @Query("select u from User u join u.roles r where r.name=?1")
    List<User> getAllByRole(String roleName);

    @Query("select u from User u join Group g on u.group.id=g.id join g.courses c join Company com on c.company.id=com.id")
    List<User> getStudentsByCompany(Long companyId);
}
