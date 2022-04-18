package com.example.lab1.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select a from Student as a")
})
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String name;

    @Basic(optional = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String surname;

    @Basic(optional = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ManyToOne(targetEntity=University.class, fetch=FetchType.EAGER)
    @JoinColumn(name="UNIVERSITY_ID")
    private University university;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @ManyToMany
    private List<Sport> sports;


    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }
}
