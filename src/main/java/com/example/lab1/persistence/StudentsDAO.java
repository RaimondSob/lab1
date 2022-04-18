package com.example.lab1.persistence;

import com.example.lab1.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class StudentsDAO {

    @Inject
    private EntityManager em;

    public void persist(Student student){
        this.em.persist(student);
    }

    public Student findOne(Long id){
        return em.find(Student.class, id);
    }

    public Student update(Student student){
        return em.merge(student);
    }
}
