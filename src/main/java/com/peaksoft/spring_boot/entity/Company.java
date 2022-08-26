package com.peaksoft.spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;
    @CreatedDate
    private LocalDate created;

    public Company(String companyName, String locatedCountry, LocalDate created) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
        this.created = created;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @JsonIgnore
    private List<Course> courses;
}
