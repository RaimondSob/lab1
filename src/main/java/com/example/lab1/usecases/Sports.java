package com.example.lab1.usecases;

import com.example.lab1.entities.Sport;
import com.example.lab1.persistence.SportsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Sports {
    @Inject
    private SportsDAO sportsDAO;

    @Getter @Setter
    private Sport sportToCreate = new Sport();

    @Getter
    private List<Sport> allSports;

    @PostConstruct
    public void init(){
        loadAllSports();
    }

    @Transactional
    public void createSport(){
        this.sportsDAO.persist(sportToCreate);
    }

    public void loadAllSports(){
        allSports = sportsDAO.loadAll();
    }
}
