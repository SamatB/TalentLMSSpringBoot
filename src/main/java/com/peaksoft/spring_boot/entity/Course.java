package com.peaksoft.spring_boot.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(generator = "course_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "duration_month")
    private Long durationMonth;
    private Boolean deleted = false;
    @CreatedDate
    private LocalDate created;

    public Course(String courseName, Long durationMonth, Boolean deleted, LocalDate created, Company company) {
        this.courseName = courseName;
        this.durationMonth = durationMonth;
        this.deleted = deleted;
        this.created = created;
        this.company = company;
    }

    @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.REFRESH, CascadeType.PERSIST})
    private Company company;

    @OneToOne(mappedBy = "course")
    private User user;

}
