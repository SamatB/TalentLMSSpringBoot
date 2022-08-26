package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("select u from User u where u.created >= : registerDate")
    List<User> findAllByCreated(@Param("registerDate") LocalDate registerDate);
//    @Query("select u from User u join u.roles r where u.created = ?1")
//    List<User>findByCreated(LocalDate startDate, LocalDate endDate);

    @Query("select u from User u join Group g on u.group.id=g.id where g.id=?1")
    List<User> getStudentsByGroupId(Long id);

    @Query("select u from User u join Course  c on u.course.id=c.id where c.id=?1")
    List<User> getStudentsByCourseId(Long id);
    @Query("select u from User u join u.roles r where r.name=?1")
    List<User> getAllByRole(String roleName);

    @Query("select u from User u join Group g on u.group.id=g.id join g.courses c join Company com on c.company.id=com.id")
    List<User> getStudentsByCompany(Long companyId);


    @Query("select u from User u join u.roles r where r.name=:roleName and (upper(u.userName)) like concat('%',:text,'%')" +
            "or upper(u.userLastname) like concat('%',:text,'%') or upper(u.email) like concat('%',:text,'%') or (u.created between :startDate and :endDAte)")
    List<User> searchAndPagination(String roleName, @Param("text") String text, Pageable pageable, LocalDate startDate, LocalDate endDate);
}
