package com.example.lab1.usecases;

import lombok.Setter;
import lombok.Getter;
import com.example.lab1.mybatis.dao.UniversityMapper;
import com.example.lab1.mybatis.model.University;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class UniversitiesMyBatis {
    @Inject
    private UniversityMapper universityMapper;

    @Getter
    private List<University> allUniversities;

    @Getter @Setter
    private University universityToCreate = new University();

    @PostConstruct
    public void init() { this.loadAllUniversities(); }

    private void loadAllUniversities() { this.allUniversities = universityMapper.selectAll();}

    @Transactional
    public String createUniversity(){
        universityMapper.insert(universityToCreate);
        return "/myBatis/universities?faces-redirect=true";
    }
}
