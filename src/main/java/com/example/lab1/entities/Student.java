package com.example.lab1.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select a from Student as a")
})
@Table(name = "STUDENT")
public class Student {

    public Student(){

    }
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

    private String lsp;

    public String getLsp(){
        return lsp;
    }

    public void setLsp(String lsp){
        this.lsp = lsp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;

        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(university, student.university);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, university);
    }

    @ManyToMany
    private List<Sport> sports;


    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

}
