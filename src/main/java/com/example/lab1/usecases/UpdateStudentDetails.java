package com.example.lab1.usecases;

import lombok.Getter;
import lombok.Setter;
import com.example.lab1.interceptors.LoggedInvocation;
import com.example.lab1.persistence.StudentsDAO;
import com.example.lab1.entities.Student;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;


@ViewScoped
@Named
@Getter @Setter
public class UpdateStudentDetails implements Serializable{

    private Student student;

    @Inject
    private StudentsDAO studentsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateStudentDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long studentId = Long.parseLong(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @Transactional
    @LoggedInvocation
    public String updateStudentSurname() {
        try{
            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "studentDetails.xhtml?faces-redirect=true&amp;studentId="+student.getId()+"&error=optimistic-lock-exception";
        }
        return "studentDetails.xhtml?faces-redirect=true&amp;studentId="+student.getId();
    }
}
