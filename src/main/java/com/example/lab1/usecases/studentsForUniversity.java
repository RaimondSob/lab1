package com.example.lab1.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

import com.example.lab1.entities.University;
import com.example.lab1.persistence.StudentsDAO;
import com.example.lab1.persistence.UniversitiesDAO;
import com.example.lab1.entities.Student;
import lombok.Getter;
import lombok.Setter;

@Model
public class studentsForUniversity {
    @Inject
    private UniversitiesDAO universitiesDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private University university;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long universityId = Long.parseLong(requestParameters.get("universityId"));
        this.university = universitiesDAO.findOne(universityId);
    }

    @Transactional
    public void createStudent() {
        studentToCreate.setUniversity(this.university);
        studentsDAO.persist(studentToCreate);
    }
}
