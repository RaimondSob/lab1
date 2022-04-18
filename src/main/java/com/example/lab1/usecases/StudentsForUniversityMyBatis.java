package com.example.lab1.usecases;

import com.example.lab1.mybatis.dao.UniversityMapper;
import com.example.lab1.mybatis.dao.StudentMapper;
import com.example.lab1.mybatis.model.Student;
import com.example.lab1.mybatis.model.University;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
public class StudentsForUniversityMyBatis {

    @Inject
    private StudentMapper studentMapper;

    @Inject
    private UniversityMapper universityMapper;

    @Getter @Setter
    private University university;

    @Getter
    private List<Student> students;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long universityId = Long.parseLong(requestParameters.get("universityId"));
        this.university = universityMapper.selectByPrimaryKey(universityId);
        setStudents(universityId);
    }

    private void setStudents(Long universityId){
        List<Student> students = studentMapper.selectAll();
        this.students = students.stream().filter(s -> s.getUniversityId() == universityId).collect(Collectors.toList());
    }

    @Transactional
    public void createStudent(){
        studentToCreate.setUniversityId(university.getId());
        studentMapper.insert(studentToCreate);
    }
}