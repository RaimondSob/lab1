package com.example.lab1.usecases;

import com.example.lab1.entities.Sport;
import com.example.lab1.entities.Student;
import com.example.lab1.interceptors.LoggedInvocation;
import com.example.lab1.persistence.SportsDAO;
import com.example.lab1.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class StudentsSports {

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private SportsDAO sportsDAO;

    @Getter @Setter
    private Student student;

    @Getter @Setter
    private Sport sportToAdd;

    @Getter @Setter
    private Long sportToAddId;

    @Getter @Setter
    private Long sportToRemoveId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long studentId = Long.parseLong(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @LoggedInvocation
    @Transactional
    public String addSport(){
        this.sportToAdd = sportsDAO.findById(sportToAddId);
        if(!student.getSports().contains(sportToAdd)){
            student.getSports().add(sportToAdd);
        }
        studentsDAO.update(student);

        return "StudentsSports?faces-redirect=true&amp;studentId="+student.getId();
    }

    @Transactional
    public void removeSport(){
        student.getSports().remove(sportsDAO.findById(sportToRemoveId));
        studentsDAO.update(student);
    }
}
