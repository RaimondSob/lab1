package com.example.lab1.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;

@Entity
@NamedQueries({
        @NamedQuery(name = "University.findAll", query = "select t from University as t")
})
@Table(name = "UNIVERSITY")

@EqualsAndHashCode
public class University {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
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

    private Integer fakultetas;

    @Basic(optional = true)
    public Integer getFakultetas() {
        return fakultetas;
    }

    public void setFakultetas(Integer fakultetas) {
        this.fakultetas = fakultetas;
    }

    @OneToMany(targetEntity=Student.class, mappedBy="university", fetch=FetchType.EAGER)
    private List<Student> students;


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
