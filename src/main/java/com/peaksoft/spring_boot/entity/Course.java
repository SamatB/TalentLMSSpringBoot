package com.peaksoft.spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
@AllArgsConstructor
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
    private transient Long companyId;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Company company;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "courses")
    @JsonIgnore
    private List<Group> groups;



    @OneToOne(mappedBy = "course", cascade = {CascadeType.MERGE})
    @JsonIgnore
    private User user;
}
