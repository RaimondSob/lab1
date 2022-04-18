package com.example.lab1.usecases;

import com.example.lab1.entities.University;
import lombok.Getter;
import lombok.Setter;
import com.example.lab1.persistence.UniversitiesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Universities {

    @Inject
    private UniversitiesDAO universitiesDAO;

    @Getter @Setter
    private University universityToCreate = new University();

    @Getter
    private List<University> allUniversities;

    @PostConstruct
    public void init(){
        loadAllUniversities();
    }

    @Transactional
    public void createUniversity(){
        this.universitiesDAO.persist(universityToCreate);
    }

    private void loadAllUniversities(){
        this.allUniversities = universitiesDAO.loadAll();
    }
}
