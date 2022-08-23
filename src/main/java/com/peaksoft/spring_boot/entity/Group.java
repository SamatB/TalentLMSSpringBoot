package com.peaksoft.spring_boot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(generator = "group_gen", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "group_gen", sequenceName = "group_seq", allocationSize = 1)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "date_of_create")
    private LocalDate dateOfCreate;
    @Column(name = "date_of_finish")
    private LocalDate dateOfFinish;
    private Boolean deleted = false;
    @CreatedDate
    private LocalDate created;

    public Group(String groupName, String dateOfCreate, String dateOfFinish, Boolean deleted, LocalDate created, List<Course> courses) {
        this.groupName = groupName;
        this.dateOfCreate = LocalDate.parse(dateOfCreate);
        this.dateOfFinish = LocalDate.parse(dateOfFinish);
        this.deleted = deleted;
        this.created = created;
        this.courses = courses;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private List<Course> courses;


    @OneToMany(mappedBy = "group", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> users;
}
