package com.peaksoft.spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(generator = "group_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_gen", sequenceName = "group_seq", allocationSize = 1)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @CreatedDate
    @Column(name = "date_of_create")
    private LocalDate dateOfCreate;
    @Column(name = "date_of_finish")
    private String dateOfFinish;
    private Boolean deleted = false;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    @JsonIgnore
    private List<Course> courses;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.REFRESH})
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> users;
}
